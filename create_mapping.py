#!/usr/bin/env python3
"""
Create a mapping file for obfuscated Minecraft class names.
This script will help identify which obfuscated names need to be replaced.
"""

import os
import re
from collections import defaultdict

def find_obfuscated_classes(directory):
    """Find all obfuscated class names in Java files."""
    obfuscated_pattern = re.compile(r'\bclass_\d+\b')
    method_pattern = re.compile(r'\bmethod_\d+\b')
    field_pattern = re.compile(r'\bfield_\d+\b')
    comp_pattern = re.compile(r'\bcomp_\d+\b')
    
    obfuscated_classes = defaultdict(set)
    obfuscated_methods = defaultdict(set)
    obfuscated_fields = defaultdict(set)
    obfuscated_comps = defaultdict(set)
    
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                filepath = os.path.join(root, file)
                try:
                    with open(filepath, 'r', encoding='utf-8') as f:
                        content = f.read()
                        
                        # Find classes
                        for match in obfuscated_pattern.finditer(content):
                            obfuscated_classes[match.group()].add(filepath)
                        
                        # Find methods
                        for match in method_pattern.finditer(content):
                            obfuscated_methods[match.group()].add(filepath)
                        
                        # Find fields
                        for match in field_pattern.finditer(content):
                            obfuscated_fields[match.group()].add(filepath)
                        
                        # Find comps
                        for match in comp_pattern.finditer(content):
                            obfuscated_comps[match.group()].add(filepath)
                
                except Exception as e:
                    print(f"Error reading {filepath}: {e}")
    
    return obfuscated_classes, obfuscated_methods, obfuscated_fields, obfuscated_comps

def main():
    """Main function."""
    yanderov_dir = 'src/main/java/dev/client/yanderov'
    
    if not os.path.exists(yanderov_dir):
        print(f"Error: Directory {yanderov_dir} not found!")
        return
    
    print("Scanning for obfuscated names...")
    classes, methods, fields, comps = find_obfuscated_classes(yanderov_dir)
    
    print(f"\nFound {len(classes)} unique obfuscated classes")
    print(f"Found {len(methods)} unique obfuscated methods")
    print(f"Found {len(fields)} unique obfuscated fields")
    print(f"Found {len(comps)} unique obfuscated comps")
    
    # Write to file
    with open('obfuscated_mapping.txt', 'w', encoding='utf-8') as f:
        f.write("=== OBFUSCATED CLASSES ===\n")
        for cls in sorted(classes.keys()):
            f.write(f"{cls} (used in {len(classes[cls])} files)\n")
            for filepath in sorted(classes[cls])[:3]:  # Show first 3 files
                f.write(f"  - {filepath}\n")
        
        f.write("\n=== OBFUSCATED METHODS ===\n")
        for method in sorted(methods.keys())[:50]:  # Show first 50
            f.write(f"{method} (used in {len(methods[method])} files)\n")
        
        f.write("\n=== OBFUSCATED FIELDS ===\n")
        for field in sorted(fields.keys())[:50]:  # Show first 50
            f.write(f"{field} (used in {len(fields[field])} files)\n")
        
        f.write("\n=== OBFUSCATED COMPS ===\n")
        for comp in sorted(comps.keys()):
            f.write(f"{comp} (used in {len(comps[comp])} files)\n")
    
    print("\nMapping written to obfuscated_mapping.txt")
    
    # Print most common ones
    print("\nMost common obfuscated classes:")
    sorted_classes = sorted(classes.items(), key=lambda x: len(x[1]), reverse=True)
    for cls, files in sorted_classes[:20]:
        print(f"  {cls}: {len(files)} files")

if __name__ == '__main__':
    main()

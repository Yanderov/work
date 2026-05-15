#!/usr/bin/env python3
"""
Fix all orphaned case statements in Java files.
This script handles decompiled switch statements that have orphaned case labels.
"""

import os
import re
import sys

def fix_orphaned_cases(content):
    """Fix orphaned case statements by converting them to proper switch statements."""
    lines = content.split('\n')
    fixed_lines = []
    i = 0
    
    while i < len(lines):
        line = lines[i]
        
        # Check if this is an orphaned case statement
        if re.match(r'^\s*case\s+\d+:\s*$', line):
            # Look backwards to find the variable that should be switched on
            indent = len(line) - len(line.lstrip())
            
            # Look for the previous line that might contain the switch variable
            switch_var = None
            for j in range(i - 1, max(0, i - 10), -1):
                prev_line = lines[j].strip()
                
                # Look for common patterns like: int x = ...; or Type x = ...;
                # or method calls that return values
                if '=' in prev_line and not prev_line.startswith('//'):
                    # Extract variable name from assignment
                    match = re.search(r'(\w+)\s*=', prev_line)
                    if match:
                        switch_var = match.group(1)
                        break
                
                # Look for variable declarations
                match = re.search(r'\b(int|byte|short|char|enum)\s+(\w+)', prev_line)
                if match:
                    switch_var = match.group(2)
                    break
            
            if not switch_var:
                # Default to a generic variable name
                switch_var = "value"
            
            # Create the switch statement
            switch_line = ' ' * indent + f'switch ({switch_var}) {{'
            fixed_lines.append(switch_line)
            
            # Add the case statement
            fixed_lines.append(line)
            
            # Look ahead for more case statements and default
            i += 1
            while i < len(lines):
                next_line = lines[i]
                next_stripped = next_line.strip()
                
                # Check if it's another case or default
                if re.match(r'^case\s+\d+:', next_stripped) or next_stripped.startswith('default:'):
                    fixed_lines.append(next_line)
                    i += 1
                    continue
                
                # Check if it's the closing brace of the switch
                if next_stripped == '}':
                    fixed_lines.append(next_line)
                    i += 1
                    break
                
                # Otherwise, it's content inside a case
                fixed_lines.append(next_line)
                i += 1
                
                # If we hit a break, continue looking for more cases
                if 'break;' in next_stripped:
                    continue
            
            continue
        
        fixed_lines.append(line)
        i += 1
    
    return '\n'.join(fixed_lines)

def process_file(filepath):
    """Process a single Java file."""
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Check if file has orphaned case statements
        if re.search(r'^\s*case\s+\d+:\s*$', content, re.MULTILINE):
            print(f"Fixing: {filepath}")
            fixed_content = fix_orphaned_cases(content)
            
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(fixed_content)
            
            return True
        
        return False
    
    except Exception as e:
        print(f"Error processing {filepath}: {e}")
        return False

def main():
    """Main function to process all Java files."""
    base_dir = 'src/main/java/dev/client/yanderov'
    
    if not os.path.exists(base_dir):
        print(f"Error: Directory {base_dir} not found!")
        sys.exit(1)
    
    fixed_count = 0
    
    # List of files with known orphaned case errors
    problem_files = [
        'display/hud/CoolDowns.java',
        'display/hud/Notifications.java',
        'display/hud/Potions.java',
        'features/impl/combat/AntiBot.java',
        'features/impl/combat/Aura.java',
        'features/impl/combat/PulseBlink.java',
        'features/impl/misc/FreeCam.java',
        'features/impl/misc/ServerHelper.java',
        'features/impl/movement/AirStuck.java',
        'features/impl/movement/Blink.java',
        'features/impl/player/AutoRespawn.java',
        'features/impl/player/EnderChestPlus.java',
        'features/impl/render/ProjectilePrediction.java',
        'main/listener/impl/EventListener.java',
        'utils/client/packet/network/Network.java',
        'utils/features/aura/target/TargetFinder.java',
        'utils/features/aura/warp/TurnsConnection.java',
        'utils/interactions/item/ItemToolkit.java',
    ]
    
    for file_path in problem_files:
        full_path = os.path.join(base_dir, file_path)
        if os.path.exists(full_path):
            if process_file(full_path):
                fixed_count += 1
        else:
            print(f"Warning: File not found: {full_path}")
    
    print(f"\nFixed {fixed_count} files with orphaned case statements")

if __name__ == '__main__':
    main()

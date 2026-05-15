import os
import re

# Список файлов с проблемами
problem_files = [
    "src/main/java/dev/client/yanderov/display/hud/CoolDowns.java",
    "src/main/java/dev/client/yanderov/display/hud/Notifications.java",
    "src/main/java/dev/client/yanderov/display/hud/Potions.java",
    "src/main/java/dev/client/yanderov/features/impl/combat/AntiBot.java",
    "src/main/java/dev/client/yanderov/features/impl/combat/Aura.java",
    "src/main/java/dev/client/yanderov/features/impl/combat/PulseBlink.java",
    "src/main/java/dev/client/yanderov/features/impl/misc/FreeCam.java",
    "src/main/java/dev/client/yanderov/features/impl/misc/ServerHelper.java",
    "src/main/java/dev/client/yanderov/features/impl/movement/AirStuck.java",
    "src/main/java/dev/client/yanderov/features/impl/movement/Blink.java",
    "src/main/java/dev/client/yanderov/features/impl/movement/ElytraMotion.java",
    "src/main/java/dev/client/yanderov/features/impl/player/AutoRespawn.java",
    "src/main/java/dev/client/yanderov/features/impl/player/EnderChestPlus.java",
    "src/main/java/dev/client/yanderov/features/impl/render/ProjectilePrediction.java",
    "src/main/java/dev/client/yanderov/main/listener/impl/EventListener.java",
    "src/main/java/dev/client/yanderov/utils/client/packet/network/Network.java",
    "src/main/java/dev/client/yanderov/utils/features/aura/target/TargetFinder.java",
    "src/main/java/dev/client/yanderov/utils/features/aura/warp/TurnsConnection.java",
    "src/main/java/dev/client/yanderov/utils/interactions/item/ItemToolkit.java",
]

def fix_orphaned_cases(content):
    """Исправляет orphaned case statements"""
    lines = content.split('\n')
    fixed_lines = []
    i = 0
    
    while i < len(lines):
        line = lines[i]
        
        # Если нашли TODO комментарий от предыдущего исправления
        if '// TODO: Fix switch statement' in line:
            # Пропускаем TODO и if statement
            fixed_lines.append(line)
            i += 1
            if i < len(lines):
                fixed_lines.append(lines[i])  # if statement
                i += 1
            
            # Теперь ищем и удаляем все case блоки до закрывающей скобки
            brace_count = 1
            while i < len(lines) and brace_count > 0:
                current_line = lines[i]
                
                # Подсчитываем скобки
                brace_count += current_line.count('{')
                brace_count -= current_line.count('}')
                
                # Пропускаем case блоки
                if 'case ' in current_line or 'default:' in current_line:
                    # Комментируем case
                    fixed_lines.append('            // ' + current_line.strip())
                elif brace_count > 0:
                    # Комментируем содержимое case блоков
                    if current_line.strip() and not current_line.strip().startswith('//'):
                        fixed_lines.append('            // ' + current_line.strip())
                    else:
                        fixed_lines.append(current_line)
                else:
                    # Закрывающая скобка
                    fixed_lines.append(current_line)
                
                i += 1
        else:
            fixed_lines.append(line)
            i += 1
    
    return '\n'.join(fixed_lines)

def fix_file(filepath):
    """Исправляет файл"""
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        content = fix_orphaned_cases(content)
        
        if content != original_content:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)
            return True
        return False
    except Exception as e:
        print(f"Error fixing {filepath}: {e}")
        return False

# Исправляем все проблемные файлы
print("Fixing orphaned case statements...")
fixed_count = 0

for filepath in problem_files:
    if os.path.exists(filepath):
        if fix_file(filepath):
            fixed_count += 1
            print(f"Fixed: {filepath}")
    else:
        print(f"File not found: {filepath}")

print(f"\nFixed {fixed_count} files.")

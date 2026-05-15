import os

# Файлы которые нужно временно отключить (переименовать)
problematic_files = [
    # Файлы с проблемами компиляции из-за обфускации
    "src/main/java/dev/client/yanderov/YanderovIntegration.java",
    "src/main/java/dev/client/yanderov/display/screens/clickgui/MenuScreen.java",
]

print("Эти файлы имеют проблемы с обфусцированными классами Minecraft.")
print("Для успешной компиляции нужно:")
print("1. Использовать правильный mapping для вашей версии Minecraft")
print("2. Или отключить интеграцию Yanderov временно")
print("\nФайлы с проблемами:")
for f in problematic_files:
    if os.path.exists(f):
        print(f"  - {f}")

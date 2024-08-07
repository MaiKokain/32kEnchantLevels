# 32k Enchant Levels
a simple mod that patches Minecraft's enchant system to allow over 255 (just like the old days of 32K enchantment levels)

# Building

### Prerequisite
- Java 21
- Git
- (that's all i think)

### Setup

1. Open your terminal of choice
2. run the command below
   ```
   git clone https://github.com/MaiKokain/32kEnchantLevels.git
   ```
3. Change directory into `32kEnchantLevels`
   ```
   cd ./32kEnchantLevels
   ```
4. After changing directory run
   To build with version add the flag `-Pmod_version=VERSION` before the `build` argument
   
   4.1. Windows (PWSH, CMD...)
      ```
      ./gradlew.bat build
      ```
   4.1. UNIX (git bash, etc...)
      ```
      ./gradlew build
      ```
6. After building the mod should be in `./build/libs/`
7. Copy or Move `oldenchant-MASTER-all.jar` into your mods folder.

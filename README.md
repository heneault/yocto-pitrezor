# yocto-pitrezor
OS linux platform for the pitrezor project (usign yocto)

This code is used to build the linux platform image for the raspberry pi zero and pi 4 to be able to run the pitrezor software at bootup.

Note: This process will download and build a large amount of software, so be sure to allow at least 50GB of space on the host system and expect the process to take several hours.

## How to build pitrezor image?

# Build Release from Source via Docker
This is the most reliable way to build your own copy of the offial release images directly from the official piTrezor Github repositories. (This process will ignore any changes you make to your own local repository by default)

The steps are:
1. [Install Docker](https://docs.docker.com/engine/installation/)
2. `git clone https://github.com/heneault/yocto-pitrezor.git`
3. `cd yocto-pitrezor`
4. `sudo ./build-pitrezor.sh TAG [raspberrypi4-64 | raspberrypi0-2w-64]` (where TAG is 1.11.2.0 for example, if left blank the script builds latest commit in master branch). You can optionnaly specify "raspberrypi4-64" or "raspberrypi0-2w-64" to build an image for one of these platform. Otherwise the default platform is pi zero.

This creates file `build/pitrezor-MACHINE-TAG.zip` .

# Build Release from Source natively
This process requires that you configure your environment to be able to use Bitbake. This allows you to make modifications to the recipe locally, as well as allowing you to modify/rebuild images without the full redownload/compile.

The steps are:
1. Install requirements on your system. (On Ubuntu 22.04 you will need to install these with something like: `sudo apt install -y gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping locales liblz4-tool zstd`)
2. Clone the repository: `git clone https://github.com/3rdIteration/yocto-pitrezor.git` (This will clone this forked repo, not the official one by heneault)
3. Nativate to the repository source folder: `cd yocto-pitrezor`
4. `./build-pitrezor-nodocker.sh TAG [[raspberrypi4 | raspberrypi4-64 | raspberrypi0-2w | raspberrypi0-2w-64]]` (where TAG is 1.11.2.0 for example, if left blank the script builds latest commit in master branch). You can optionnaly specify "raspberrypi4-64" or "raspberrypi0-2w-64" to build an image for one of these platform. Otherwise the default platform is pi zero.

## Hardware & Build Note
The fbcp-ili9341 driver is not currently compatible with building or running natively in a 64bit environment. What this means is that if you intend to use a piTrezor in this way, you need to ensure that you are building x86 software for your system. (Typically this means simply leaving "-64" off the end of the platform name, so for a Raspberry Pi 4, your target platform would be `raspberrypi4`) _There is no real security or performance penalty from running piTrezor in x86 mode as opposed to x64..._

## Configuration File Note
The piTrezor configuration file can be found in the `/boot` partition of the SD card after flashing it. (This will be the only partition visible if you are using something like a Windows PC)

An example of the configuration file can be seen below.

    # Scale factor of display when using hdmi or an FBCP-ILI9341 LCD HAT (1 to 16 inclusively)
    export TREZOR_OLED_SCALE=2
    
    # Type of oled to use
    #  NO OLED                  = 0
    #  OLED_ADAFRUIT_I2C_128x64 = 1
    #  OLED_SEEED_I2C_128x64    = 2
    #  OLED_SH1106_I2C_128x64   = 3
    #  OLED_ADAFRUIT_SPI_128x64 = 4
    #  OLED_SH1106_SPI_128x64   = 5
    export TREZOR_OLED_TYPE=3
    
    # Flip the display vertically. Set to 0 or 1 
    export TREZOR_OLED_FLIP=0
    
    # Set gpio number to use for the yes/no buttons
    export TREZOR_GPIO_YES=16
    export TREZOR_GPIO_NO=12
    
    # Enable fbcp-ili9431 based display if required (Don't set this to 1 if you have also enabled an SPI based display above)
    export ENABLE_FBCPILI9341_DISPLAY=1

By default, `ENABLE_FBCPILI9341_DISPLAY` option will support the [Waveshare 240x240 1.3inch LCD HAT](https://www.waveshare.com/1.3inch-lcd-hat.htm) that is used with the [SeedSigner](https://seedsigner.com/). If you want to use a different hat, you will need to modify the fbcp-ili9341 recipe with the cmake arguments found here: https://github.com/juj/fbcp-ili9341



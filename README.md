# yocto-pitrezor
OS linux platform for the pitrezor project (usign yocto)

This code is used to build the linux platform image for the raspberry pi zero to be able to run the pitrezor software at bootup.

## How to build pitrezor image?

1. [Install Docker](https://docs.docker.com/engine/installation/)
2. `git clone https://github.com/heneault/yocto-pitrezor.git`
3. `cd yocto-pitrezor`
4. `sudo ./build-pitrezor.sh TAG` (where TAG is 1.8.2.0 for example, if left blank the script builds latest commit in master branch)

This creates file `build/pitrezor-TAG.zip` .


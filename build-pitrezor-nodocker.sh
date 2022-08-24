#!/bin/bash

TAG=${1:-master}
MACHINE=${2:-raspberrypi0-wifi} # raspberrypi0-wifi (default), raspberrypi0, raspberrypi4 or raspberrypi0-2w-64
IMAGE=pitrezor-build
IMGFILE=imgs/pitrezor-$MACHINE-$TAG.img

# Install Requirements for Ubuntu 22.04 build environment
sudo apt-get install -y gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping locales liblz4-tool zstd

# Build Image
mkdir -p imgs
rm -v $(pwd)/imgs:/imgs:z $IMAGE

git checkout $TAG
git submodule update --init --recursive
sed -i -e 's/^MACHINE.*/MACHINE ??= \"'$MACHINE'\"/' build/conf/local.conf
. poky/oe-init-build-env build
bitbake pitrezor-image
cd ../
cp build/tmp/deploy/images/$MACHINE/pitrezor-image-$MACHINE.rpi-sdimg $IMGFILE
zip -m -j -9 ${IMGFILE%.img}.zip $IMGFILE

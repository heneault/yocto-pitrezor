#!/bin/bash

TAG=${1:-master}
MACHINE=${2:-raspberrypi0-wifi} # raspberrypi0-wifi (default), raspberrypi0, raspberrypi4 or raspberrypi0-2w-64
IMAGE=pitrezor-build
IMGFILE=imgs/pitrezor-$MACHINE-$TAG.img

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

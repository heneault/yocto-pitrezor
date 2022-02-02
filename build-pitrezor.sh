#!/bin/bash
set -e

TAG=${1:-master}
MACHINE=${2:-raspberrypi0-wifi} # raspberrypi0-wifi (default), raspberrypi4 or raspberrypi0-2w-64
IMAGE=pitrezor-build
IMGFILE=imgs/pitrezor-$MACHINE-$TAG.img
DOCKER_UID=30000

mkdir -p imgs
chown $DOCKER_UID:$DOCKER_UID imgs

docker build -t $IMAGE .

docker run -t --rm -v $(pwd)/imgs:/imgs:z $IMAGE /bin/bash -c "\
git clone https://github.com/heneault/yocto-pitrezor.git && \
cd yocto-pitrezor && \
git checkout $TAG && \
git submodule update --init --recursive && \
sed -i -e 's/^MACHINE.*/MACHINE ??= \"$MACHINE\"/' build/conf/local.conf && \
. poky/oe-init-build-env build && \
bitbake pitrezor-image && \
cp tmp/deploy/images/$MACHINE/pitrezor-image-$MACHINE.rpi-sdimg /$IMGFILE"

zip -m -j -9 ${IMGFILE%.img}.zip $IMGFILE

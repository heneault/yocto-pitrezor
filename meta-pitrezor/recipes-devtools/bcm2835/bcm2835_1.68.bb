DESCRIPTION = "Package that provides access to GPIO and other IO\
functions on the Broadcom BCM 2835 chip, allowing access to the\
GPIO pins on the 26 pin IDE plug on the RPi board"
SECTION = "base"
HOMEPAGE = "http://www.open.com.au/mikem/bcm2835"
AUTHOR = "Mike McCauley (mikem@open.com.au)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e49f4652534af377a713df3d9dec60cb"

COMPATIBLE_MACHINE = "^rpi$"

SRC_URI = "http://www.airspayce.com/mikem/bcm2835/bcm2835-${PV}.tar.gz \
           file://fix_i2c_core_clock.patch \
           "

SRC_URI[md5sum] = "96ee0c2b078a77bfe4aa749fd056157a"
SRC_URI[sha256sum] = "b7eda59fd7bc3cb97fdac14e8455e86ddad75006d69270358ac8d00108e369a9"

inherit autotools

ALLOW_EMPTY:${PN} = "1"

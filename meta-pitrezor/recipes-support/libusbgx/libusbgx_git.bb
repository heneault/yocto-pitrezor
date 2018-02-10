SUMMARY = "USB Gadget Configfs Library (next)"

LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "libconfig"

inherit autotools pkgconfig

SRCREV = "904b04c4b46ec673197ad1d8291a0aabe6a34f52"
SRC_URI = "git://github.com/libusbgx/libusbgx.git \
          "
S = "${WORKDIR}/git"

PACKAGES = "${PN}-example ${PN} ${PN}-dev ${PN}-dbg"

FILES_${PN}-example = "${bindir}/*"

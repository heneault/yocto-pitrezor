SUMMARY = "Pitrezor application"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://legacy/COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "libsdl2 libsdl2-image bcm2835 protobuf-native python3-protobuf-native python3-six-native python3-click-native python3-trezor-native dos2unix-native python3-mako-native python3-munch-native libconfig python3-typing-extensions-native"
RDEPENDS:${PN} = "rng-tools dos2unix libegl-mesa libgles2-mesa mesa-megadriver libgbm libdrm"

inherit pkgconfig python3native siteinfo

SRC_URI = "git://github.com/heneault/trezor-firmware.git;branch=pitrezor \
           file://start_pitrezor \
           file://pitrezor.config \
          "

SRCREV = "27057e8ae70e6d4e19e5a213f820b4907583c612"

S = "${WORKDIR}/git"

do_configure() {
}

do_compile() {
  export EMULATOR=1
  export PIZERO=1
  export CPUFLAGS=""
  export ARCH_BITS=${SITEINFO_BITS}
  export CC_FOR_BUILD=${BUILD_CC}
  cd legacy
  make vendor
  make -C emulator/pizero
  make -C emulator
  make -C vendor/nanopb/generator/proto
  make -C firmware/protob
  make
  make -C firmware trezor.elf
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${datadir}/pitrezor
    install -m 0755 ${WORKDIR}/start_pitrezor ${D}${bindir}
    install -m 0766 ${B}/legacy/firmware/trezor.elf ${D}${bindir}/pitrezor
    install -m 0444 ${WORKDIR}/pitrezor.config ${D}${datadir}/pitrezor
    unix2dos ${D}${datadir}/pitrezor/pitrezor.config
}

FILES_${PN} = "${bindir} ${datadir}/pitrezor"


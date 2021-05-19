SUMMARY = "Pitrezor application"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://legacy/COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "libsdl2 libsdl2-image bcm2835 protobuf-native python3-protobuf-native python3-six-native python3-click-native python3-trezor-native dos2unix-native python3-mako-native python3-munch-native libconfig"
RDEPENDS_${PN} = "rng-tools dos2unix libegl-mesa libgles2-mesa mesa-megadriver libgbm libdrm"

inherit pkgconfig python3native

SRC_URI = "git://github.com/heneault/trezor-firmware.git;branch=pitrezor \
           file://start_pitrezor \
           file://pitrezor.config \
          "

SRCREV = "670c28eac114b041537a60899c830837a5f93a89"

S = "${WORKDIR}/git"

do_configure() {
}

do_compile() {
  export EMULATOR=1
  export PIZERO=1
  export CPUFLAGS=""
  export RANDOM_DEV_FILE="/dev/random"
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


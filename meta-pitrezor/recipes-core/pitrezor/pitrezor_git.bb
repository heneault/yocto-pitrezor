SUMMARY = "Pitrezor application"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "libsdl libusbgx bcm2835 protobuf-native python3-protobuf-native python3-six-native"
RDEPENDS_${PN} = "rng-tools"

inherit pkgconfig python3native

SRC_URI = "gitsm:///github.com/heneault/trezor-mcu.git;branch=pizero \
           file://start_pitrezor \
           file://pitrezor.config \
          "

SRCREV = "5974180199dfa3b687faed96a5e34f065098da22"

S = "${WORKDIR}/git"

do_compile() {
  export EMULATOR=1
  export TRANSPORT=USBG
  export PIZERO=1
  export CPUFLAGS=""
  export USE_RANDOM=1
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
    install -m 0766 ${B}/firmware/trezor.elf ${D}${bindir}/pitrezor
    install -m 0444 ${WORKDIR}/pitrezor.config ${D}${datadir}/pitrezor
}

FILES_${PN} = "${bindir} ${datadir}/pitrezor"


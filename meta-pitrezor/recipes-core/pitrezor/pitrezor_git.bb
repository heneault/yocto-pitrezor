SUMMARY = "Pitrezor application"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://legacy/COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "libsdl2 libsdl2-image bcm2835 protobuf-native python3-protobuf-native python3-six-native python3-click-native python3-trezor-native dos2unix-native python3-mako-native python3-munch-native libconfig python3-typing-extensions-native fbcp-ili9341"
RDEPENDS:${PN} = "rng-tools dos2unix libegl-mesa libgles2-mesa mesa-megadriver libgbm libdrm fbcp-ili9341"

inherit pkgconfig python3native siteinfo

SRC_URI = "git://github.com/heneault/trezor-firmware.git;branch=pitrezor \
           file://start_pitrezor \
           file://pitrezor.config \
          "

SRCREV = "48b0673ee61a06aceb038e8c35915a03e959b8f9"

S = "${WORKDIR}/git"

do_configure() {
}

# Compile the Bitcoin-Only firmware first, then the Universal. (And completely clean between builds)
do_compile() {
  export BITCOIN_ONLY=1

  export EMULATOR=1
  export PIZERO=1
  export CPUFLAGS=""
  export ARCH_BITS=${SITEINFO_BITS}
  export CC_FOR_BUILD=${BUILD_CC}
  
  cd legacy
  make clean
  cd firmware
  make clean
  cd protob
  make clean
  cd ../../
  make clean
  cd emulator
  make clean
  cd pizero
  make clean
  cd ../../
  
  make vendor
  make -C emulator/pizero
  make -C emulator
  make -C vendor/nanopb/generator/proto
  make -C firmware/protob
  make
  make -C firmware trezor.elf
  cp firmware/trezor.elf firmware/trezor-bitcoinonly.firmware
  
  export BITCOIN_ONLY=0

  make clean
  cd firmware
  make clean
  cd protob
  make clean
  cd ../../
  make clean
  cd emulator
  make clean
  cd pizero
  make clean
  cd ../../

  make vendor
  make -C emulator/pizero
  make -C emulator
  make -C vendor/nanopb/generator/proto
  make -C firmware/protob
  make
  make -C firmware trezor.elf
  cp firmware/trezor.elf firmware/trezor-universal.firmware
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${datadir}/pitrezor
    install -m 0755 ${WORKDIR}/start_pitrezor ${D}${bindir}
    install -m 0766 ${B}/legacy/firmware/trezor-universal.firmware ${D}${bindir}/pitrezor-universal
    install -m 0766 ${B}/legacy/firmware/trezor-bitcoinonly.firmware ${D}${bindir}/pitrezor-bitcoinonly
    install -m 0444 ${WORKDIR}/pitrezor.config ${D}${datadir}/pitrezor
    unix2dos ${D}${datadir}/pitrezor/pitrezor.config
}

FILES_${PN} = "${bindir} ${datadir}/pitrezor"


SUMMARY = "Client side implementation for TREZOR-compatible Bitcoin hardware wallets."

HOMEPAGE = "https://github.com/trezor/python-trezor"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "gitsm://github.com/trezor/python-trezor.git"
SRCREV = "252f946f409fc87d6b9f462682ef36219b96ece3"
S="${WORKDIR}/git"

SRC_URI[md5sum] = ""
SRC_URI[sha256sum] = ""

BBCLASSEXTEND = "native nativesdk"

inherit setuptools3

DEPENDS += " protobuf-native python3-protobuf-native python3-six-native "

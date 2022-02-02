SUMMARY = "A dot-accessible dictionary (a la JavaScript objects)."
DEPENDS = "${PYTHON_PN}-pbr-native"
HOMEPAGE = "https://github.com/Infinidat/munch"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f0291cd32b2d6a91d9b19970c94d0a46"

SRC_URI[sha256sum] = "2d735f6f24d4dba3417fa448cae40c6e896ec1fdab6cdb5e6510999758a4dbd2"

PYPI_PACKAGE = "munch"

inherit pypi setuptools3
RDEPENDS:${PN} += "${PYTHON_PN}-pip"
BBCLASSEXTEND = "native nativesdk"


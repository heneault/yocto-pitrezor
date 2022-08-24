SUMMARY = "small helper script to start fbcp-ili9341 application"

SRC_URI = "file://start_mirrorhdmi \
           file://LICENSE.txt \
          "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=b90b0b30f2dc2ac04dc31b925b74f0eb"

do_install() {
    install -m 0755 ${WORKDIR}/start_mirrorhdmi ${D}${bindir}
}

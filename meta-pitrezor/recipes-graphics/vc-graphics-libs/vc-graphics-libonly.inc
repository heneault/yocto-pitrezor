DESCRIPTION = "Graphics libraries for BCM2835. (Libs required for fbcp-ili9341 that don't conflict with PiTrezor)"
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE;md5=86e53f5f5909ee66900418028de11780"

PROVIDES = ""
COMPATIBLE_MACHINE = "^rpi$"

INHIBIT_DEFAULT_DEPS = "1"

include recipes-bsp/common/raspberrypi-firmware.inc

SRC_URI += ""

S = "${RPIFW_S}/${VCDIR}"

INCPR = "r1"

do_install () {
 
  install -d ${D}${libdir}
  # note: -H option to deref symlinked .so
  cp -R -H lib/* ${D}${libdir}

  install -d ${D}${includedir}
  cp -R include/* ${D}${includedir}
  
  # Remove files from includes that conflict with libraries in the PiTrezor Build
  rm ${D}${includedir}/EGL/egl.h
  rm ${D}${includedir}/EGL/eglext.h
  rm ${D}${includedir}/EGL/eglplatform.h
  rm ${D}${includedir}/KHR/khrplatform.h
  rm ${D}${includedir}/GLES/glext.h
  rm ${D}${includedir}/GLES/glplatform.h
  rm ${D}${includedir}/GLES/gl.h
  rm ${D}${includedir}/GLES2/gl2.h
  rm ${D}${includedir}/GLES2/gl2ext.h
  rm ${D}${includedir}/GLES2/gl2platform.h
  
  # Remove files from libs that conflict with libraries in the PiTrezor Build
  rm ${D}${libdir}/libEGL.so
  rm ${D}${libdir}/libGLESv1_CM.so
  rm ${D}${libdir}/libGLESv2.so
}

# These are proprietary binaries generated elsewhere so don't check ldflags
INSANE_SKIP:${PN} = "ldflags"

INITSCRIPT_NAME = "vchiq.sh"
INITSCRIPT_PARAMS = "start 03 S ."

FILES:${PN} = "${bindir}/* \
               ${libdir}/lib*.so \
               ${sysconfdir}/init.d \
               ${libdir}/plugins"
FILES:${PN}-dev = "${libdir}/pkgconfig \
                   ${includedir}"
FILES:${PN}-dbg += "${libdir}/plugins/.debug"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://yocto.patch"
DEPENDS = "virtual/egl"
PACKAGECONFIG = "gles2"

PACKAGECONFIG = "kmsdrm gles2"
EXTRA_OECONF_append = " --disable-video-rpi "
DEPENDS += "udev"
BBCLASSEXTEND=""


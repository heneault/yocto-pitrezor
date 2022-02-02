FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
           file://usb.cfg \
           file://usb_inode.patch \
           "

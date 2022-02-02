include pitrezor-image.bb 
IMAGE_FEATURES += " debug-tweaks "

customize_image_debug() {
  sed -i '/^DROPBEAR_EXTRA_ARGS=/ s/-w//' ${IMAGE_ROOTFS}${sysconfdir}/default/dropbear
  echo brcmfmac >> ${IMAGE_ROOTFS}${sysconfdir}/modules-load.d/brcmfmac.conf
  echo auto wlan0 >> ${IMAGE_ROOTFS}${sysconfdir}/network/interfaces
  ln -s ../init.d/networking ${IMAGE_ROOTFS}${sysconfdir}/rc5.d/S05network
}

ROOTFS_POSTPROCESS_COMMAND:append = " customize_image_debug; "

IMAGE_INSTALL += " iw wireless-regdb-static wpa-supplicant linux-firmware-rpidistro-bcm43430 linux-firmware-rpidistro-bcm43455 kernel-module-brcmfmac dropbear gdbserver strace linux-firmware-rpidistro-bcm43436 linux-firmware-rpidistro-bcm43436s"


# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# Include modules in rootfs
IMAGE_INSTALL += " \
	kernel-module-gadgetfs \
	kernel-module-dwc2 \
        "

IMAGE_INSTALL:append:raspberrypi4 = " kernel-module-v3d libudev "

IMAGE_INSTALL += " pitrezor "

customize_image() {
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*keymap.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootlogd
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*urandom
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*banner.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*populate-volatile.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*dmesg.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootmisc.sh

  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*syslog
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*rmnologin.sh
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*mountnfs.sh
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*stop-bootlogd
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*networking

  echo "pitrezor" > ${IMAGE_ROOTFS}/etc/hostname

  echo "tz:5:respawn:/usr/bin/start_pitrezor" >> ${IMAGE_ROOTFS}/etc/inittab

  echo "LABEL=boot /boot vfat defaults 0 0" >> ${IMAGE_ROOTFS}/etc/fstab

  echo "mkdir /dev/gadget && mount -t gadgetfs none /dev/gadget" >> ${IMAGE_ROOTFS}/etc/rcS.d/S10mountgadget
  chmod a+x ${IMAGE_ROOTFS}/etc/rcS.d/S10mountgadget
}

ROOTFS_POSTPROCESS_COMMAND:append = " customize_image; "

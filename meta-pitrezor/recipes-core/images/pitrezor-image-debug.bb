include pitrezor-image.bb 

IMAGE_INSTALL += " \
        kernel-module-brcmfmac \
        "

IMAGE_INSTALL += " linux-firmware-bcm43430 wireless-tools wpa-supplicant dropbear gdbserver strace "


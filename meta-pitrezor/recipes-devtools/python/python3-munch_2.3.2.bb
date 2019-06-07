SUMMARY = "A dot-accessible dictionary (a la JavaScript objects)."

HOMEPAGE = "https://github.com/Infinidat/munch"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f0291cd32b2d6a91d9b19970c94d0a46"

SRC_URI[md5sum] = "539311fdfc6419381b6f38d0695f01ee"
SRC_URI[sha256sum] = "6ae3d26b837feacf732fb8aa5b842130da1daf221f5af9f9d4b2a0a6414b0d51"

BBCLASSEXTEND = "native nativesdk"

inherit pypi setuptools3

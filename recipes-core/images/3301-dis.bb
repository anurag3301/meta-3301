SUMMARY = "Meta layer for learning and trying stuff out"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "package-management"

EXTRA_IMAGE_FEATURES += "tools-sdk debug-tweaks"

DISTRO_FEATURES += " wifi"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    htop \
    packagegroup-core-buildessential \
    gof \
    kbd \
    glibc-utils \
    localedef \
    glibc-localedata-en-us \
    shellprofile \
    shadow \
    util-linux-agetty \
    openssh \
    bluez5 \
    i2c-tools \
    python3-smbus \
    bridge-utils \
    dhcpcd \
    iptables \
    wpa-supplicant \
    linux-firmware \
    linux-firmware-rpidistro-bcm43430 \
    pciutils \
    kernel-module-brcmfmac \
    packagegroup-base-wifi \
    kernel-modules \
"

GLIBC_GENERATE_LOCALES = "en_US.UTF-8"
IMAGE_LINGUAS = "en-us"

set_locale_postinst() {
    mkdir -p ${IMAGE_ROOTFS}/etc/profile.d
    cat >> ${IMAGE_ROOTFS}/etc/profile.d/locale.sh << EOF
export LANG="en_US.UTF-8"
export LC_ALL="en_US.UTF-8"
export LANGUAGE="en_US.UTF-8"
EOF

    # Also set the system default locale in /etc/locale.conf
    mkdir -p ${IMAGE_ROOTFS}/etc
    cat >> ${IMAGE_ROOTFS}/etc/locale.conf << EOF
LANG=en_US.UTF-8
LC_ALL=en_US.UTF-8
LANGUAGE=en_US.UTF-8
EOF
}

set_autologin() {
    sed -i 's/^id:.*:initdefault:/id:3:initdefault:/' ${IMAGE_ROOTFS}/etc/inittab
    sed -i 's#^\([1-9]\):.*respawn:/.*getty.*tty1#1:12345:respawn:/sbin/agetty --autologin root --noclear tty1#' ${IMAGE_ROOTFS}/etc/inittab
}

ROOTFS_POSTPROCESS_COMMAND += "set_locale_postinst; set_autologin;"


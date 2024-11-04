SUMMARY = "Meta layer for learning and trying stuff out"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "package-management"

EXTRA_IMAGE_FEATURES += "tools-sdk"
EXTRA_IMAGE_FEATURES += "debug-tweaks"

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
    # Ensure the default runlevel is set to 3
    sed -i 's/^id:.*:initdefault:/id:3:initdefault:/' ${IMAGE_ROOTFS}/etc/inittab

    # Modify the getty line for tty1 to use agetty with auto-login
    sed -i 's#^\([1-9]\):.*respawn:/.*getty.*tty1#1:12345:respawn:/sbin/agetty --autologin root --noclear tty1#' ${IMAGE_ROOTFS}/etc/inittab
}

ROOTFS_POSTPROCESS_COMMAND += "set_locale_postinst; set_autologin;"

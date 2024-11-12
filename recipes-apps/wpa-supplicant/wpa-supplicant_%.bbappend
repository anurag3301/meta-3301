# In meta-your-layer/recipes-connectivity/wpa-supplicant/wpa-supplicant_%.bbappend

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant.conf"

do_install:append() {
    install -m 0600 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/
}

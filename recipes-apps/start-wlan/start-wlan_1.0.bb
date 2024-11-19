SUMMARY = "Starts the wpa supplicant and dhcpcd to setup the wifi"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://wifi"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wifi ${D}${sysconfdir}/init.d
}

RDEPENDS:${PN} = "bash"

INITSCRIPT_NAME = "wifi"
INITSCRIPT_PARAMS = "defaults 35"

pkg_postinst_${PN}() {
    if [ -z "$D" ]; then
        # Enable the service only on the target system, not in the build environment
        update-rc.d wifi defaults
        update-rc.d wifi enable
    fi
}

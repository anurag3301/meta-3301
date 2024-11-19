SUMMARY = "Init script which mounts the loopback devices"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d

SRC_URI = "file://loopmount"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/loopmount ${D}${sysconfdir}/init.d
}

RDEPENDS:${PN} = "bash"

INITSCRIPT_NAME = "loopmount"
INITSCRIPT_PARAMS = "defaults"

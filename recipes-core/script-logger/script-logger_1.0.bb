SUMMARY = "Addes the script-logger script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://scriptlog"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/scriptlog ${D}${bindir}/scriptlog
}

FILES_${PN} = "${bindir}/*"
RDEPENDS:${PN} = "bash"

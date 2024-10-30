SUMMARY = "Sets the .profile file which autostarts htop"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://.profile"

do_install() {
    install -d ${D}/home/root
    install -m 0644 ${WORKDIR}/.profile ${D}/home/root
}

FILES:${PN} = "/home/root/.profile"

SUMMARY = "Runs the updater"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://updater \
           file://updater-crontab"

do_install() {
    install -d ${D}/home/root
    install -m 0755 ${WORKDIR}/updater ${D}/home/root

    install -d ${D}/etc/cron.d
    install -m 0644 ${WORKDIR}/updater-crontab ${D}/etc/cron.d/updater-crontab
}

RDEPENDS:${PN} = "bash cronie"
FILES:${PN} = "/home/root/updater /etc/cron.d/updater-crontab"

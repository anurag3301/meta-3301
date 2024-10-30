SUMMARY = "Interactive process viewer"
DESCRIPTION = "htop is an interactive process viewer for Unix systems."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools 

DEPENDS = "ncurses"

SRC_URI = "https://github.com/htop-dev/htop/releases/download/${PV}/htop-${PV}.tar.xz"
SRC_URI[sha256sum] = "a69acf9b42ff592c4861010fce7d8006805f0d6ef0e8ee647a6ee6e59b743d5c"

PACKAGES += "${PN}-icons"

FILES:${PN} = "${bindir}/* \
               ${datadir}/applications \
               ${datadir}/pixmaps \
               ${mandir}/man1/*"

FILES:${PN}-icons = "${datadir}/icons/hicolor/scalable/apps/htop.svg"

RDEPENDS:${PN} += "${PN}-icons"

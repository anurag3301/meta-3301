SUMMARY = "Game Of Life"
DESCRIPTION = "The Game of Life, also known as Conway's Game of Life or simply Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/anurag3301/Dumps.git;protocol=https;branch=main \
            file://gof_continuous.patch"
SRC_URI[sha256sum] = "8c8dd01d05480a68058bb6567528ed81ba1589eafcfe266e3b9ea4941961f686"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "ncurses"

do_fetch[depends] = "git-native:do_populate_sysroot"

do_configure() {
    :
}

do_compile() {
    cd c/game_of_life
    ${CC} ${CFLAGS} main.c -o gol ${LDFLAGS} -lncursesw
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 c/game_of_life/gol ${D}${bindir}/gol
}

FILES_${PN} = "${bindir}/*"


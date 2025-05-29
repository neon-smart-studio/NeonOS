DESCRIPTION = "NeonOS boot config"
LICENSE = "MIT"
SRC_URI += "file://config.txt"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/boot
    install -m 0644 ${WORKDIR}/config.txt ${D}${sysconfdir}/boot/config.txt
}
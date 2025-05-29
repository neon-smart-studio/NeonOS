SUMMARY = "W-Link Web Control Server"
LICENSE = "MIT"
SRC_URI = "git://github.com/neon-smart-studio/W-Link_IoT_Server.git;branch=master"
SRCREV = "${AUTOREV}"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c5bdac945a40f4c9653440c2fab6d88"

S = "${WORKDIR}/git"

PACKAGES = "${PN}"
FILES:${PN} += "/opt/wlink"

inherit systemd

do_install() {
    install -d ${D}/opt/w-link-iot-server
    cp -r ${S}/* ${D}/opt/w-link-iot-server/

    install -d ${IMAGE_ROOTFS}/etc/systemd/system
    install -m 0644 ${THISDIR}/files/w-link-iot-server.service ${IMAGE_ROOTFS}/etc/systemd/system/w-link-iot-server.service
    install -d ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants
    ln -sf ../w-link-iot-server.service ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/w-link-iot-server.service
}

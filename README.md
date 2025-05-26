# NeonOS
基於 Yocto 開發之智慧家庭中樞 NeonOS

進入目錄
cd NeonOS

修改編譯平台
nano build/conf/local.conf
選raspberrypi4-64

新增現有layer
source oe-init-build-env ./build
ex: bitbake-layers add-layer ./meta-openembedded/meta-networking

或是也可以
git clone -b dunfell https://git.openembedded.org/meta-openembedded

新增自訂layer
bitbake-layers create-layer ../<你的名稱>
nano meta-neon/conf/layer.conf

修改layers目錄
nano build/conf/bblayers.conf
類似這樣
BBLAYERS ?= " \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  ${TOPDIR}/../poky/meta-yocto-bsp \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-raspberrypi \
  ${TOPDIR}/../meta-neon \
"

編譯
cd build

bitbake core-image-minimal

最後在以下目錄可以找到image

build/tmp-glibc/deploy/images/<你的架構>/core-image-minimal-<你的架構>-X.rootfs.wic.bz2/
解壓縮就能透過win32DislImager燒進去emmc/SD

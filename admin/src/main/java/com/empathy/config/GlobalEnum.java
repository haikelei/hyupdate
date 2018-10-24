package com.empathy.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/11/23 10:43
 * @email zhoujian699@126.com
 * @desc
 **/
public interface GlobalEnum {

    /**
     * 加工材质
     */
    @Getter
    enum Texture {
        NONE(0, "无"),
        ALUFER_ALLOY(1, "铝合金"),
        COOPER(2, "铜"),
        COOPER_ALLOY(3, "铜合金"),
        PLASTIC(4, "强化塑料"),
        TITANIUM_ALLOY(5, "钛合金"),
        STEREOPLASM_ALLOY(6, "硬质合金"),
        WOOD(7, "木材"),
        GRAPHITE(8, "石墨"),
        HIGH_CARBON_STEEL(9, "淬硬高碳钢"),
        LOW_CARBON_STEEL(10, "淬硬低碳钢"),
        CAST_IRON(11, "铸铁"),
        POWDER_METALLURGY(12, "粉末冶金"),
        NODULAR_CAST_IRON(13, "球墨铸铁");
        private int code;
        private String memo;

        Texture(int code, String memo) {
            this.code = code;
            this.memo = memo;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Node {

        private int key;
        private String value;
        private String ext;

    }

    /**
     * 内冷
     */
    @Getter
    enum InnerCool {
        YES(1, "是", 5),
        NO(0, "否", 0),;
        private int code;
        private String memo;
        private int ratio;//系数

        InnerCool(int code, String memo, int ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            InnerCool[] cools = InnerCool.values();
            for (InnerCool cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }

    }

    /**
     * 过中心否
     */
    @Getter
    enum OverCenter {
        YES(1, "是", 1),
        NO(0, "否", 0),;
        private int code;
        private String memo;
        private int ratio;//系数

        OverCenter(int code, String memo, int ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            OverCenter[] cools = OverCenter.values();
            for (OverCenter cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }
    }

    /**
     * 换片成本价
     */
    @Getter
    enum ChangeCostPrice {
        YES(1, "是", 1),
        NO(0, "否", 0),;
        private int code;
        private String memo;
        private int ratio;//系数

        ChangeCostPrice(int code, String memo, int ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            ChangeCostPrice[] cools = ChangeCostPrice.values();
            for (ChangeCostPrice cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }
    }

    @Getter
    enum Extend {
        EXT1(1, "快丝", 5.0),
        EXT2(2, "平磨", 11.0),
        EXT3(3, "校验", 10.0);
        private int code;
        private String memo;
        private double value;

        Extend(int code, String memo, double value) {
            this.code = code;
            this.value = value;
            this.memo = memo;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            Extend[] cools = Extend.values();
            for (Extend cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getValue()));
            }
            return nodes;
        }
    }

    /**
     * 技术等级
     */
    @Getter
    enum TechLevel {
        ONE(1, "1级", 0.8),
        TWO(2, "2级", 1),
        THREE(3, "3级", 1.2),
        FOUR(4, "4级", 1.4),
        FIVE(5, "5级", 1.6),
        SIX(6, "6级", 1.8),
        SEVEN(7, "7级", 5),
        EIGHT(8, "8级", 10);
        private int code;
        private String memo;
        private double ratio;//系数

        TechLevel(int code, String memo, double ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            TechLevel[] cools = TechLevel.values();
            for (TechLevel cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }
    }

    /**
     * 法兰种类
     */
    @Getter
    enum Flange {
        LARGE(1, "大直径", 2.5),
        SMALL(0, "小直径", 1),;
        private int code;
        private String memo;
        private double time;//加工时间

        Flange(int code, String memo, double time) {
            this.code = code;
            this.memo = memo;
            this.time = time;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            Flange[] cools = Flange.values();
            for (Flange cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getTime()));
            }
            return nodes;
        }
    }

    /**
     * 中间刀具装夹方式
     */
    @Getter
    enum InstallWay {
        LARGE(1, "测固式", 1),
        SMALL(2, "后拉式", 2),;
        private int code;
        private String memo;
        private double ratio;//系数

        InstallWay(int code, String memo, double ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            InstallWay[] cools = InstallWay.values();
            for (InstallWay cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }
    }

    /**
     * 刀片种类
     */
    @Getter
    enum KnifeType {
        LARGE(1, "标准", 1),
        SMALL(2, "非标", 1.5),;
        private int code;
        private String memo;
        private double ratio;//系数

        KnifeType(int code, String memo, double ratio) {
            this.code = code;
            this.memo = memo;
            this.ratio = ratio;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            KnifeType[] cools = KnifeType.values();
            for (KnifeType cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getRatio()));
            }
            return nodes;
        }
    }

    /**
     * 刀夹型号
     */
    @Getter
    enum KnifeModel {
        DEGREE15(1, "15度", 160),
        DEGREE45(2, "45度", 160),
        DEGREE60(3, "60度", 160),
        DEGREE90(4, "90度", 160),;
        private int code;
        private String memo;
        private double price;//价格

        KnifeModel(int code, String memo, double price) {
            this.code = code;
            this.memo = memo;
            this.price = price;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            KnifeModel[] cools = KnifeModel.values();
            for (KnifeModel cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getPrice()));
            }
            return nodes;
        }
    }

    /**
     * 镗头型号
     */
    @Getter
    enum BoreModel {
        L148(1, "L148", 2700),
        SUPER(2, "超尔镗头", 1000),;
        private int code;
        private String memo;
        private double price;//价格

        BoreModel(int code, String memo, double price) {
            this.code = code;
            this.memo = memo;
            this.price = price;
        }

        /**
         * @return
         */
        public static List<Node> getList() {
            List<Node> nodes = new ArrayList<>();
            BoreModel[] cools = BoreModel.values();
            for (BoreModel cool : cools) {
                nodes.add(new Node(cool.getCode(), cool.getMemo(), "" + cool.getPrice()));
            }
            return nodes;
        }
    }
}

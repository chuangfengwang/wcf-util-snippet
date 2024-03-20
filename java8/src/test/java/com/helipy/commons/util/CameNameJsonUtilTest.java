package com.helipy.commons.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Pack:       com.helipy.commons.util
 * File:       CameNameJsonUtilTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-09-25 11:48
 */
@Slf4j
public class CameNameJsonUtilTest {

    @Test
    void parse2dJsonTest() {
        String responseMsg = "{\"status\":\"ok\",\"in_time\":0.18405914306640625,\"results\":[[0,334975,52164,347286,174153,52002,0,336225,345778,127714,12977,84793,218091,74157,12227,290336,0,0,94074,84538,325866,48753,0,74165,263544,105919,284373,259764,60043,173747,289970,166842,336406,325643,132532,345792,0,318379,127707,333125,166844,52069,48768,334953,260256,371077,173081,172464,284383,0,310203,334965,52003,284370,309920,41864,351525,96762,0,144568,93410,371062,0,138100,315718,47362,74153,0,48717,332129,9833,0,60119,0,255291,195538,0,47420,0,48722,0,95396,0,263541,59918,254505,143983,267651,68838,66815,336240,271920,12283,151081,112545,64508,0,160035,318173,12276,328859,7807,318571,0,103775,85209,224962,52072,52068,318568,332292]]}";
        JSONObject jsonObject = JSON.parseObject(responseMsg);
        int[][] wordArray = jsonObject.getObject("results", int[][].class);
        System.out.println(JSON.toJSONString(wordArray));
    }

    @Test
    void longListToArray() {
        // 创建一个包含一些长整数的列表
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        // 使用Stream API将列表转换为long[]
        long[] longArray = list.stream()
                .mapToLong(Long::longValue)
                .toArray();
        // 打印结果
        System.out.println(Arrays.toString(longArray));
    }

    @Test
    void parseExpResult() {
        String responseMsg = "{\"status\":\"ok\",\"in_time\":2280.801296234131,\"data\":[{\"doc\":\"职位：国内电商运营。\\t偏好：收银主管,商场收银,服装店收银,,。\\t教育：大专,汽车运用技术。\\t工作：直播运营,1.负责主播的招募。;2.负责主播的直播的内容策划。以及丰富主播直播间玩法。;3.负责小团队（3-5人）团队的日常工作及管理。;4.负责主播的流量数据分析，以及协助主播去做账号数据;直播运营,1.负责发掘和招募外站平台的主播和艺人;2.负责主播直播数据分析，协助主播去做账号数据。;3.负责主播的直播方案，自己直播间内容输出。\",\"keyword_cands_scores\":{\"主播直播间\":0.8340418275200591,\"直播间玩法\":0.8344234240347497,\"汽车运用技术\":0.7445434100226596,\"直播运营\":0.8462027897927775,\"协助主播\":0.8698274935231383,\"做账\":0.7672827924113867,\"服装店收银\":0.7761092720717653,\"主播直播\":0.827775548829629,\"流量数据分析\":0.8417704532816137,\"内容策划\":0.8309524772122988,\"国内电商运营\":0.8429910717239417,\"主管\":0.7387646361923219,\"直播间\":0.806993766818979,\"服装店\":0.6927253035903659,\"收银主管\":0.7948517631686394,\"主播\":0.8118944790587197,\"流量数据\":0.7955114567961656,\"商场收银\":0.7914213486157106,\"数据分析\":0.8132050790341945},\"field_keyword_cands\":{\"expect_id\":548412440,\"geek_id\":535243163,\"position_name\":[\"国内电商运营\"],\"bob_v2\":[],\"expose_cards\":[],\"work_emphasis\":[\"主管\",\"商场收银\",\"服装店\",\"收银主管\",\"服装店收银\"],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"直播运营\",\"直播间\",\"主播直播间\",\"主播直播\",\"直播间玩法\",\"流量数据分析\",\"数据分析\",\"内容策划\",\"做账\",\"主播\",\"流量数据\",\"协助主播\"]},{\"rank\":2,\"keyword_cands\":[\"直播运营\",\"艺人\",\"直播间\",\"主播直播\",\"直播方案\",\"直播数据\",\"内容输出\",\"数据分析\",\"直播数据分析\",\"做账\",\"主播\",\"外站\",\"协助主播\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"汽车运用技术\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：经纪人/星探。\\t偏好：,,。\\t教育：大专,电子商务技术。\\t工作：经纪人,负责招聘且带主播，包括不限于直播引导短视频孵化步骤，视频剪辑。\",\"keyword_cands_scores\":{\"视频剪辑\":0.7948283950796526,\"带主播\":0.8295306376406637,\"电子商务技术\":0.8042262627152177,\"剪辑\":0.7148422332264561,\"短视频\":0.7812102278233517,\"经纪人/星探\":0.8916636490140161,\"经纪人\":0.8110729544368823,\"短视频孵化\":0.8519216013364943,\"负责招聘\":0.8360280495703023,\"步骤\":0.6927430999167762,\"主播\":0.7811159663943452},\"field_keyword_cands\":{\"expect_id\":557573688,\"geek_id\":122519680,\"position_name\":[\"经纪人/星探\"],\"bob_v2\":[],\"expose_cards\":[],\"work_emphasis\":[],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"经纪人\",\"视频剪辑\",\"带主播\",\"剪辑\",\"短视频\",\"短视频孵化\",\"负责招聘\",\"步骤\",\"主播\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"电子商务技术\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：直播运营。\\t偏好：,,情感直播,才艺直播,聊天直播,主播经纪人,虎牙。\\t教育：高中,。\\t工作：经纪人,直播平台官方运营;负责线上线下招募主播维护主播培训主播提升主播综合直播技巧和收益等另外带新人运营和面试新运营;主持人/主播/dj,每天准时开播和维护粉丝。\",\"keyword_cands_scores\":{\"招募主播\":0.8795862206818659,\"直播运营\":0.8413125379963023,\"主播经纪\":0.8564305530402356,\"官方\":0.7686996380031559,\"运营\":0.8184698781150739,\"收益\":0.7422559379799268,\"聊天直播\":0.8448732950695745,\"新人\":0.7665068110174511,\"维护主播\":0.8908683355141289,\"技巧\":0.7156039956319576,\"主播维护\":0.8945483621478434,\"经纪人\":0.811788829076004,\"主播\":0.8462899248505449,\"情感直播\":0.8502372672280643,\"直播技巧\":0.8509696230560179,\"培训主播\":0.8679614374237284,\"主播培训\":0.8710835790626827,\"人运营\":0.8354767421296861,\"线上线下\":0.7564114014828643,\"直播平台\":0.7902275709635816,\"主播经纪人\":0.8601729239142036,\"才艺直播\":0.8510115239271382},\"field_keyword_cands\":{\"expect_id\":460650668,\"geek_id\":64288372,\"position_name\":[\"直播运营\"],\"bob_v2\":[\"主播经纪\",\"经纪人\",\"聊天直播\",\"主播\",\"主播经纪人\",\"才艺直播\",\"情感直播\"],\"expose_cards\":[],\"work_emphasis\":[],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"经纪人\",\"线上线下\",\"直播技巧\",\"维护主播\",\"培训主播\",\"官方\",\"技巧\",\"主播维护\",\"招募主播\",\"运营\",\"主播培训\",\"收益\",\"人运营\",\"主播\",\"直播平台\",\"新人\"]},{\"rank\":2,\"keyword_cands\":[\"主持人/主播/dj\",\"粉丝\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：经纪人/星探。\\t偏好：,监督管理,新员工入职培训,门店,消防设施安全,所有人员,短视频演员,不限。\\t教育：本科,会计。\\t工作：防损员,1，负责新员工入职培训。;2，负责门店日常管理工作，;3.对门店消防设施安全进行检查和排查;4.对门店所有人员进行监督管理。\",\"keyword_cands_scores\":{\"新员工入职培训\":0.8649561169901698,\"入职\":0.7467823341269603,\"设施安全\":0.7976649710921427,\"消防设施\":0.7977376239057236,\"日常管理\":0.8406949599431066,\"员工入职\":0.8129152460539795,\"员工入职培训\":0.8487127822039885,\"新员工入职\":0.8361636815085033,\"监督\":0.7563226995214406,\"会计\":0.6996424799264722,\"排查\":0.7454967682868286,\"视频演员\":0.7423335290595319,\"短视频演员\":0.7428563918569145,\"防损员\":0.7188120740982664,\"设施\":0.7193200412425088,\"管理工作\":0.8269398107795906,\"经纪人/星探\":0.7875193020692828,\"消防设施安全\":0.8269270904971902,\"短视频\":0.6587197864302157,\"监督管理\":0.8351259737705339,\"入职培训\":0.815455964751985,\"所有人员\":0.850304678207298},\"field_keyword_cands\":{\"expect_id\":755704153,\"geek_id\":545758161,\"position_name\":[\"经纪人/星探\"],\"bob_v2\":[\"短视频\",\"视频演员\",\"短视频演员\"],\"expose_cards\":[\"新员工入职培训\",\"消防设施安全\",\"设施安全\",\"消防设施\",\"员工入职\",\"员工入职培训\",\"监督管理\",\"新员工入职\",\"入职培训\",\"所有人员\"],\"work_emphasis\":[],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"防损员\",\"新员工入职培训\",\"消防设施安全\",\"设施\",\"入职\",\"排查\",\"设施安全\",\"消防设施\",\"管理工作\",\"日常管理\",\"员工入职\",\"员工入职培训\",\"监督管理\",\"新员工入职\",\"监督\",\"入职培训\",\"所有人员\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"会计\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：经纪人/星探。\\t偏好：,,。\\t教育：中专/中技,数控编程。\\t工作：新媒体运营,视频剪辑，发布短视频app平台，跟患者沟通;,日常招募主播，培养主播，规划主播直播板块和带来更好的直播效果等等。\",\"keyword_cands_scores\":{\"视频剪辑\":0.8083250982113863,\"短视频app\":0.8296753737853424,\"数控编程\":0.7406616330651322,\"app平台\":0.7892963829469867,\"新媒体运营\":0.846158707993053,\"患者沟通\":0.7984797150278379,\"剪辑\":0.7276728967621084,\"短视频\":0.7844977754648109,\"app\":0.6619550663919453,\"经纪人/星探\":0.8549292790019131,\"视频app\":0.811865688188932},\"field_keyword_cands\":{\"expect_id\":460800267,\"geek_id\":564662431,\"position_name\":[\"经纪人/星探\"],\"bob_v2\":[],\"expose_cards\":[],\"work_emphasis\":[],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"新媒体运营\",\"视频剪辑\",\"短视频app\",\"app平台\",\"患者沟通\",\"剪辑\",\"短视频\",\"app\",\"视频app\"]},{\"rank\":2,\"keyword_cands\":[\"\",\"板块\",\"主播直播\",\"直播效果\",\"招募主播\",\"主播\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"数控编程\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：收银。\\t偏好：超市收银,便利店收银,水果外卖,。\\t教育：中专/中技,。\\t工作：,。\",\"keyword_cands_scores\":{\"收银\":0.8317331163037396,\"便利店\":0.7593525712484712,\"超市收银\":0.8519367185338882,\"水果外卖\":0.792447184141249,\"便利店收银\":0.8592901257132702},\"field_keyword_cands\":{\"expect_id\":550977232,\"geek_id\":589081021,\"position_name\":[\"收银\"],\"bob_v2\":[],\"expose_cards\":[\"水果外卖\"],\"work_emphasis\":[\"便利店\",\"超市收银\",\"便利店收银\"],\"search_queries\":[\"水果外卖\"],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：传菜员。\\t偏好：iqc,生产计划与物料控制,生产设备管理,mqc,餐饮,。\\t教育：初中及以下,。\",\"keyword_cands_scores\":{\"生产设备\":0.8027747255820402,\"生产计划与物料控制\":0.8991944191750716,\"iqc\":0.8016185741449599,\"mqc\":0.8060658823703176,\"设备管理\":0.8418148270972745,\"生产计划\":0.8405651833918821,\"生产计划与物料\":0.8879134301423128,\"物料控制\":0.8590771321966378,\"传菜员\":0.7720720240589267,\"生产设备管理\":0.8535061993370797},\"field_keyword_cands\":{\"expect_id\":545509064,\"geek_id\":587837178,\"position_name\":[\"传菜员\"],\"bob_v2\":[],\"expose_cards\":[],\"work_emphasis\":[\"生产设备\",\"生产计划与物料控制\",\"iqc\",\"mqc\",\"设备管理\",\"生产计划\",\"生产计划与物料\",\"物料控制\",\"生产设备管理\"],\"search_queries\":[],\"geek_work\":[],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"\"]}],\"geek_advantage\":[]}},{\"doc\":\"职位：。\\t偏好：电商售前客服,产品优化,店铺运营,产品推广,抖音客服,。\",\"keyword_cands_scores\":{\"客服\":0.8367067673183799,\"产品推广\":0.8710763482702487,\"售前客服\":0.887153349984009,\"售前\":0.7601330470837916,\"抖音\":0.6978276814419927,\"电商售前客服\":0.9095392476913162,\"店铺运营\":0.8632624836547826,\"抖音客服\":0.8663727595352477,\"运营\":0.8022824635242234,\"电商售前\":0.8388032262322624,\"产品优化\":0.8583655297676666},\"field_keyword_cands\":{\"expect_id\":49339043,\"geek_id\":0,\"position_name\":[\"\"],\"bob_v2\":[],\"expose_cards\":[\"抖音\",\"客服\",\"抖音客服\"],\"work_emphasis\":[\"客服\",\"产品推广\",\"售前客服\",\"售前\",\"电商售前客服\",\"店铺运营\",\"运营\",\"电商售前\",\"产品优化\"],\"search_queries\":[\"抖音\",\"客服\",\"抖音客服\"],\"geek_work\":[],\"geek_project\":[],\"geek_edu\":[],\"geek_advantage\":[]}},{\"doc\":\"职位：会计。\\t偏好：交互体验设计,设计师,axure,销售,。\\t教育：大专,财务。\\t工作：会计顾问,1.按月（季）根据会计账目进行报税工作;2.了解客户账务情况，解决客户基础财税问题;3.负责对接客户，根据客户级别进行客户管理，维护并负责客户续费工作;4.为客户提供专业的税务咨询，及时发现客户的财税风险问题，挖掘客户潜在需求;5.及时向客户传达税务相关要求，配合好企业做税务检查，协助企业进行相应的税务沟通;咨询会计,1.负责与客户沟通，建立客户与主办会计之间的联系，及时解答客户税务方面的问题。;2.每月截图社保和公积金申报人员，和客户核对人员是否有变动，核对后进行变更并申报。;3.和客户对接一次性业务的办理及变更所需要的材料，及时和工商部门进行对接（新注册公司/核名/变更股权/变更地址/办理人力资源证及劳务派遣证等）;4.新办企业营业执照下来后，安排银行开户及领盘领票，新注册个体户执照下来后，安排助理进行税务登记及领盘领票。\",\"keyword_cands_scores\":{\"会计顾问\":0.7954693749657674,\"挖掘客户\":0.7792977517468508,\"客户基础\":0.8371721025211811,\"会计账目\":0.795403616779897,\"体验设计\":0.6995463346663575,\"发现客户\":0.809058703298023,\"会计账\":0.7679517253059103,\"交互体验\":0.6341764233461819,\"会计\":0.7696553202717721,\"提供专业\":0.7987231220137926,\"续费工作\":0.7977942161907864,\"财务\":0.7131120006776772,\"潜在需求\":0.7616219647963673,\"税务咨询\":0.8260449717807259,\"协助企业\":0.8402138013114564,\"级别\":0.6699049245303355,\"销售\":0.659817571065155,\"税务检查\":0.848286789713115,\"对接客户\":0.8453747610619043,\"交互体验设计\":0.7148556796003549,\"axure\":0.6397886503263652,\"客户管理\":0.8402784121658637,\"客户续费\":0.8160433781897484,\"财税问题\":0.8295074838403742,\"设计师\":0.7017908395625908,\"税务\":0.8073302407478007,\"税务沟通\":0.8526131811214918},\"field_keyword_cands\":{\"expect_id\":53281202,\"geek_id\":57145190,\"position_name\":[\"会计\"],\"bob_v2\":[],\"expose_cards\":[\"销售\"],\"work_emphasis\":[\"设计师\",\"交互体验设计\",\"体验设计\",\"axure\",\"交互体验\"],\"search_queries\":[\"销售\"],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"会计顾问\",\"挖掘客户\",\"客户基础\",\"会计账目\",\"发现客户\",\"会计账\",\"会计\",\"提供专业\",\"续费工作\",\"潜在需求\",\"税务咨询\",\"协助企业\",\"级别\",\"税务检查\",\"对接客户\",\"客户管理\",\"客户续费\",\"财税问题\",\"税务\",\"税务沟通\"]},{\"rank\":2,\"keyword_cands\":[\"咨询会计\",\"变更\",\"和客户对接\",\"客户对接\",\"客户沟通\",\"税务方面\",\"劳务派遣\",\"进行变更\",\"助理\",\"变动\",\"会计\",\"派遣证\",\"地址\",\"执照\",\"办理人\",\"银行开户\",\"人力资源证\",\"税务登记\",\"申报人员\",\"与客户沟通\",\"个体户\",\"办企业\",\"公积金\",\"劳务\",\"核对人员\",\"主办会计\",\"人力资源\",\"税务\",\"注册公司\",\"变更地址\",\"营业执照\",\"变更股权\"]},{\"rank\":3,\"keyword_cands\":[\"主办会计\",\"一般纳税人\",\"原始凭证\",\"纳税人\",\"客户沟通\",\"社保及公积金\",\"编制记账凭证\",\"汇算清缴\",\"凭证\",\"整理原始凭证\",\"记账凭证\",\"账务处理\",\"装订成册\",\"规模\",\"公积金\",\"外勤\",\"税务\",\"税务申报\",\"整理票据\",\"工商年报\",\"年报\"]}],\"geek_project\":[],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"财务\"]}],\"geek_advantage\":[]}}]}";
        JSONObject jsonObject = JSON.parseObject(responseMsg);
        System.out.println(jsonObject.getList("data", Object.class));
    }

    @Test
    void parseExpResult2() {
        String responseMsg = "{\"status\":\"ok\",\"in_time\":1411.2319946289062,\"data\":[{\"doc\":\"职位：hrbp。\\t偏好：,,综合岗位,hrbp经验,团队搭建经验,百人团队bp经验,医疗健康,电商,业务部门,教育。\\t教育：本科,财务管理。\\t工作：hrbp,准撰写，于每年4季度完成事业部全员任职资格评价体系的搭建与评价考核，应用于次年员工调岗调薪激励中。;7、事业群企业文化活动：员工关怀、节日福利、季度半年度团建活动策划实施、日常荣誉积分制评比等;hrbp,dp共建共识,帮助员工通过各项工具实现资深成长跟新。 ;4、人才数量质量把控:西安团队0-1组建工作,做好hc管理,月度招聘需求目标沟通,开发各种免费招聘渠道,保障满编率;参与基层和核心骨干岗位面试,就岗位、文化适配度等基于建议;合理制定淘汰策略。 ;三、文化层面;1、文化价值观传递:组织西安企业文化培训落地,结合岗位价值观传递企业核心价值观,进行职业规划塑造。;2、文化活动:定期生日会、团建的举办,输出相应短视频/h5宣传,落地企业文化,增加员工归属感及节日仪式感。 ;四、团队建设及带训;1、人事团队合理规划与搭建,下属最少1人,最多4人。 ;2、团队管理和高效率任务回收,分工明确,起到标杆部门作用。 ;主要业绩: ;区域从0-1团队、人事行政规章制度的搭建;组织流程和企业文化落地与升级;人才梯队、人才库建设与储备;协助业务层面进行区域战略落地与实施。\\t项目：度完成事业部全员任职资格评价体系的搭建。\",\"keyword_cands_scores\":{\"文化活动\":0.7912663200830706,\"hrbp经验\":0.7789095270217546,\"评价\":0.7186657381118863,\"医疗\":0.686702758927924,\"全员\":0.7090962703792723,\"活动策划实施\":0.8248600723891605,\"策划实施\":0.7834001946346585,\"百人团队bp经验\":0.8644618584483751,\"综合岗位\":0.7916263604530036,\"业务部门\":0.8113160045795227,\"百人团队bp\":0.8339226765545836,\"激励\":0.6833303425291283,\"企业文化\":0.8593468271788516,\"半年度\":0.7629263129451903,\"医疗健康\":0.7227364806450169,\"活动策划\":0.7991752078981511,\"百人团队\":0.8142596903682127,\"调岗调薪\":0.7142608221964255,\"bp经验\":0.7854562920870073,\"评比\":0.7322251992701637,\"事业群\":0.7860322654159587,\"业务部\":0.7892322137735286,\"搭建经验\":0.8082537557364187,\"企业文化活动\":0.8483684582620517,\"团队搭建经验\":0.8452912603432953,\"财务管理\":0.7562359801461032,\"评价考核\":0.748613792667415,\"评价体系\":0.7714230706369457,\"事业部\":0.7447192590096084,\"hrbp\":0.7362359099008771,\"团队搭建\":0.8173349905123908,\"员工关怀\":0.8010813224547515,\"综合岗\":0.7580498861450473,\"荣誉\":0.6749710603019039,\"积分制\":0.7291731994725167,\"关怀\":0.6914740600963549},\"field_keyword_cands\":{\"expect_id\":251034527,\"geek_id\":533174440,\"position_name\":[\"hrbp\"],\"bob_v2\":[\"业务部门\",\"百人团队bp\",\"hrbp经验\",\"bp经验\",\"医疗\",\"hrbp\",\"团队搭建\",\"搭建经验\",\"医疗健康\",\"业务部\",\"百人团队bp经验\",\"团队搭建经验\",\"综合岗位\",\"综合岗\",\"百人团队\"],\"expose_cards\":[],\"work_emphasis\":[],\"search_queries\":[],\"geek_work\":[{\"rank\":1,\"keyword_cands\":[\"hrbp\",\"文化活动\",\"评价\",\"全员\",\"活动策划实施\",\"策划实施\",\"激励\",\"企业文化\",\"半年度\",\"活动策划\",\"调岗调薪\",\"评比\",\"事业群\",\"企业文化活动\",\"评价考核\",\"评价体系\",\"事业部\",\"员工关怀\",\"荣誉\",\"积分制\",\"关怀\"]},{\"rank\":2,\"keyword_cands\":[\"hrbp\",\"团队建设\",\"文化活动\",\"西安团队\",\"组织流程\",\"把控\",\"传递\",\"满编率\",\"岗位价值\",\"举办\",\"业务层面\",\"梯队\",\"企业文化\",\"作用\",\"标杆\",\"基层\",\"归属感\",\"文化落地\",\"人才库\",\"人事行政\",\"文化价值观\",\"升级\",\"共建\",\"仪式感\",\"核心骨干\",\"高效率\",\"共识\",\"塑造\",\"短视频\",\"企业文化培训\",\"区域战略\",\"人才梯队\",\"战略落地\",\"团队管理\",\"文化培训\",\"层面\",\"适配度\",\"价值观\",\"业务层\",\"培训落地\",\"质量把控\",\"企业文化落地\",\"组建\",\"骨干\",\"淘汰\",\"职业规划\",\"生日会\",\"储备\"]},{\"rank\":3,\"keyword_cands\":[\"人力资源专员/助理\",\"向相关部门\",\"公司业务\",\"公司业务部\",\"家政厨师\",\"外部招聘\",\"备案\",\"家政\",\"厨师\",\"公司选址\",\"业务部门\",\"总经办\",\"法规\",\"程度\",\"各大高校\",\"税务\",\"了解市场\",\"人力成本\",\"解释\",\"所学知识\",\"工作人员\",\"内勤工作\",\"员工宿舍\",\"市场人才\",\"人才市场\",\"主任\",\"申报税\",\"业务部\",\"领导\",\"申报税务\",\"支出\",\"税务信息\",\"动向\",\"内勤\",\"宿舍\",\"总经办主任\",\"备案信息\",\"负担\",\"领导团队\",\"费用预算\"]}],\"geek_project\":[{\"rank\":1,\"keyword_cands\":[\"全员\",\"事业部\",\"评价体系\",\"评价\"]}],\"geek_edu\":[{\"rank\":1,\"keyword_cands\":[\"财务管理\"]}],\"geek_advantage\":[]},\"individual_data\":{\"geek_work_keywords\":[\"人力资源专员/助理\",\"企业文化落地\",\"公司业务部\",\"向相关部门\",\"企业文化活动\",\"工作人员\",\"组织流程\",\"业务部门\",\"企业文化培训\",\"战略落地\",\"公司业务\",\"活动策划实施\",\"内勤工作\",\"文化落地\",\"公司选址\",\"职业规划\",\"业务部\",\"企业文化\",\"团队管理\",\"人才梯队\",\"外部招聘\",\"人力成本\",\"西安团队\",\"活动策划\",\"员工关怀\",\"税务信息\",\"培训落地\",\"人才库\",\"员工宿舍\",\"人事行政\",\"积分制\",\"团队建设\",\"业务层面\",\"了解市场\",\"区域战略\",\"半年度\",\"质量把控\",\"评比\",\"岗位价值\",\"策划实施\",\"评价体系\",\"市场人才\",\"文化培训\",\"高效率\",\"各大高校\",\"事业群\",\"梯队\",\"业务层\",\"费用预算\",\"文化活动\",\"总经办主任\",\"评价考核\",\"领导团队\",\"家政厨师\",\"所学知识\",\"人才市场\",\"申报税务\",\"调岗调薪\",\"文化价值观\",\"核心骨干\",\"组建\",\"hrbp\",\"全员\",\"备案信息\",\"层面\",\"传递\",\"适配度\",\"生日会\",\"申报税\",\"支出\",\"评价\",\"激励\",\"总经办\",\"负担\",\"厨师\",\"荣誉\",\"价值观\",\"升级\",\"事业部\",\"基层\",\"归属感\",\"塑造\",\"仪式感\",\"满编率\",\"把控\",\"共建\",\"动向\",\"淘汰\",\"举办\",\"骨干\",\"税务\",\"主任\",\"解释\",\"短视频\",\"宿舍\",\"标杆\",\"储备\",\"家政\",\"法规\",\"内勤\"],\"geek_project_keywords\":[\"评价体系\",\"评价\",\"事业部\",\"全员\"]}}]}";
        JSONObject jsonObject = JSON.parseObject(responseMsg);
        System.out.println(jsonObject.getList("data", Object.class));
    }

    @Test
    void keyLowerCamelToUnderScoreObject1() {
        // 两层结构测试
        String origMsg = "{\"id\":351280,\"expect_id\":57122455,\"geek_id\":59155003,\"city\":101010100,\"position\":140301,\"degree\":206,\"low_salary\":8,\"high_salary\":9,\"work_date8\":20160101,\"work_year\":0,\"rev_wy\":\"7:2023\",\"apply_status\":2,\"fresh_graduate\":0,\"skills\":\"\",\"hunter_visible\":0,\"district_code\":110114,\"business_code\":\"509,581\",\"status\":0,\"geek_unified\":\"\",\"age\":\"28:2019\",\"job_hopping\":\"\",\"school_id\":\"0\",\"birthday\":\"19911101\",\"is_junior_to_university\":0,\"setting_value\":30,\"major_code\":0,\"related_positions\":\"140311,140311\",\"modify_time\":\"2023-07-14 10:54:51\",\"act_time\":\"2023-09-25 11:56:07\",\"add_time\":\"2019-04-02 22:33:25\",\"update_time\":\"2023-09-25 11:56:07\",\"gender\":1,\"sec_longitude\":\"Ra-xjylb0AMmoO0N-Lf8Iw~~\",\"sec_latitude\":\"yAJeDwXg_YQ42ZZe_yM6hA~~\",\"deleted\":0,\"stu_l3_code\":\"\",\"ums_ts_\":\"2023-09-25 11:56:07.308\",\"ums_id_\":1759217812924,\"ums_op_\":\"u\",\"ums_uid_\":\"DStream\",\"ums_protocol_type__\":\"data_increment_data\",\"ums_protocol_version__\":\"1.3\",\"ums_schema_dstype__\":\"mysql\",\"ums_schema_dsname__\":\"rcd_expectrecalldata_0811\",\"ums_schema_dbname__\":\"rcd_expectrecalldata\",\"ums_schema_tbname__\":\"recall_expect_3\",\"ums_schema_fields__\":null,\"ums_before__\":\"{\\\"id\\\":351280,\\\"expect_id\\\":57122455,\\\"geek_id\\\":59155003,\\\"city\\\":101010100,\\\"position\\\":140301,\\\"degree\\\":206,\\\"low_salary\\\":8,\\\"high_salary\\\":9,\\\"work_date8\\\":20160101,\\\"work_year\\\":0,\\\"rev_wy\\\":\\\"7:2023\\\",\\\"apply_status\\\":2,\\\"fresh_graduate\\\":0,\\\"skills\\\":\\\"\\\",\\\"hunter_visible\\\":0,\\\"district_code\\\":110114,\\\"business_code\\\":\\\"509,581\\\",\\\"status\\\":0,\\\"geek_unified\\\":\\\"\\\",\\\"age\\\":\\\"28:2019\\\",\\\"job_hopping\\\":\\\"\\\",\\\"school_id\\\":\\\"0\\\",\\\"birthday\\\":\\\"19911101\\\",\\\"is_junior_to_university\\\":0,\\\"setting_value\\\":30,\\\"major_code\\\":0,\\\"related_positions\\\":\\\"140311,140311\\\",\\\"modify_time\\\":\\\"2023-07-14 10:54:51\\\",\\\"act_time\\\":\\\"2023-09-25 11:52:02\\\",\\\"add_time\\\":\\\"2019-04-02 22:33:25\\\",\\\"update_time\\\":\\\"2023-09-25 11:52:02\\\",\\\"gender\\\":1,\\\"sec_longitude\\\":\\\"Ra-xjylb0AMmoO0N-Lf8Iw~~\\\",\\\"sec_latitude\\\":\\\"yAJeDwXg_YQ42ZZe_yM6hA~~\\\",\\\"deleted\\\":0,\\\"stu_l3_code\\\":\\\"\\\"}\"}";
        JSONObject jsonObject = JSON.parseObject(origMsg);
        BinLogUtil.BinLogEvent<RecallExpectFields> binLogEvent = BinLogUtil.parserBinLog(jsonObject, RecallExpectFields.class);
        String msg = JSON.toJSONString(binLogEvent);
        JSONObject newJsonObj = CamelNameJsonUtil.keyLowerCamelToUnderScore(JSON.parseObject(msg), 0);
        System.out.println(newJsonObj.toJSONString());
    }


    @Test
    void keyLowerCamelToUnderScoreList1() {
        // 两层结构测试
        String origMsg = "{\n" +
                "    \"bobV2\": [\n" +
                "        \"综合岗位\",\n" +
                "        \"HRBP经验\",\n" +
                "        \"团队搭建经验\",\n" +
                "        \"百人团队BP经验\",\n" +
                "        \"医疗健康\",\n" +
                "        \"本地生活/O2O\",\n" +
                "        \"电商\",\n" +
                "        \"业务部门\",\n" +
                "        \"教育\"\n" +
                "    ],\n" +
                "    \"expectId\": 251034527,\n" +
                "    \"exposeCards\": [\n" +
                "        \"\"\n" +
                "    ],\n" +
                "    \"fieldKeywords\": {\n" +
                "        \"bobV2\": [\n" +
                "            \"综合岗位\",\n" +
                "            \"团队搭建\",\n" +
                "            \"bp经验\",\n" +
                "            \"团队搭建经验\",\n" +
                "            \"搭建经验\",\n" +
                "            \"百人团队bp\",\n" +
                "            \"医疗\",\n" +
                "            \"业务部门\",\n" +
                "            \"hrbp\",\n" +
                "            \"百人团队\",\n" +
                "            \"业务部\",\n" +
                "            \"hrbp经验\",\n" +
                "            \"综合岗\",\n" +
                "            \"百人团队bp经验\"\n" +
                "        ],\n" +
                "        \"expectId\": 251034527,\n" +
                "        \"exposeCards\": [],\n" +
                "        \"geekAdvantage\": [],\n" +
                "        \"geekEdu\": [\n" +
                "            {\n" +
                "                \"keywordCands\": [\n" +
                "                    \"财务管理\"\n" +
                "                ],\n" +
                "                \"rank\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"geekId\": 533174440,\n" +
                "        \"geekProject\": [\n" +
                "            {\n" +
                "                \"keywordCands\": [],\n" +
                "                \"rank\": 1\n" +
                "            }\n" +
                "        ],\n" +
                "        \"geekWork\": [\n" +
                "            {\n" +
                "                \"keywordCands\": [\n" +
                "                    \"hrbp\",\n" +
                "                    \"活动策划实施\",\n" +
                "                    \"评比\",\n" +
                "                    \"荣誉\",\n" +
                "                    \"评价\",\n" +
                "                    \"活动策划\",\n" +
                "                    \"员工关怀\",\n" +
                "                    \"激励\",\n" +
                "                    \"事业部\",\n" +
                "                    \"企业文化活动\",\n" +
                "                    \"评价考核\",\n" +
                "                    \"企业文化\",\n" +
                "                    \"全员\",\n" +
                "                    \"调岗调薪\",\n" +
                "                    \"积分制\",\n" +
                "                    \"文化活动\",\n" +
                "                    \"关怀\",\n" +
                "                    \"事业群\",\n" +
                "                    \"评价体系\",\n" +
                "                    \"策划实施\",\n" +
                "                    \"半年度\"\n" +
                "                ],\n" +
                "                \"rank\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"keywordCands\": [\n" +
                "                    \"hrbp\",\n" +
                "                    \"作用\",\n" +
                "                    \"基层\",\n" +
                "                    \"适配度\",\n" +
                "                    \"人才梯队\",\n" +
                "                    \"免费\",\n" +
                "                    \"短视频\",\n" +
                "                    \"归属感\",\n" +
                "                    \"战略落地\",\n" +
                "                    \"核心骨干\",\n" +
                "                    \"仪式感\",\n" +
                "                    \"升级\",\n" +
                "                    \"生日会\",\n" +
                "                    \"文化落地\",\n" +
                "                    \"职业规划\",\n" +
                "                    \"文化培训\",\n" +
                "                    \"企业文化培训\",\n" +
                "                    \"团队管理\",\n" +
                "                    \"人事行政\",\n" +
                "                    \"企业文化落地\",\n" +
                "                    \"文化价值观\",\n" +
                "                    \"传递\",\n" +
                "                    \"共建\",\n" +
                "                    \"人才库\",\n" +
                "                    \"把控\",\n" +
                "                    \"业务层面\",\n" +
                "                    \"组建\",\n" +
                "                    \"骨干\",\n" +
                "                    \"岗位价值\",\n" +
                "                    \"举办\",\n" +
                "                    \"西安团队\",\n" +
                "                    \"企业文化\",\n" +
                "                    \"满编率\",\n" +
                "                    \"业务层\",\n" +
                "                    \"塑造\",\n" +
                "                    \"淘汰\",\n" +
                "                    \"质量把控\",\n" +
                "                    \"文化活动\",\n" +
                "                    \"培训落地\",\n" +
                "                    \"标杆\",\n" +
                "                    \"层面\",\n" +
                "                    \"高效率\",\n" +
                "                    \"储备\",\n" +
                "                    \"区域战略\",\n" +
                "                    \"团队建设\",\n" +
                "                    \"组织流程\",\n" +
                "                    \"梯队\",\n" +
                "                    \"共识\",\n" +
                "                    \"价值观\"\n" +
                "                ],\n" +
                "                \"rank\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"keywordCands\": [\n" +
                "                    \"人力资源专员/助理\",\n" +
                "                    \"总经办\",\n" +
                "                    \"公司业务\",\n" +
                "                    \"人才市场\",\n" +
                "                    \"了解市场\",\n" +
                "                    \"市场人才\",\n" +
                "                    \"所学知识\",\n" +
                "                    \"员工宿舍\",\n" +
                "                    \"法规\",\n" +
                "                    \"公司业务部\",\n" +
                "                    \"总经办主任\",\n" +
                "                    \"业务部门\",\n" +
                "                    \"税务\",\n" +
                "                    \"内勤工作\",\n" +
                "                    \"税务信息\",\n" +
                "                    \"宿舍\",\n" +
                "                    \"人力成本\",\n" +
                "                    \"家政\",\n" +
                "                    \"费用预算\",\n" +
                "                    \"厨师\",\n" +
                "                    \"工作人员\",\n" +
                "                    \"负担\",\n" +
                "                    \"支出\",\n" +
                "                    \"向相关部门\",\n" +
                "                    \"业务部\",\n" +
                "                    \"动向\",\n" +
                "                    \"备案信息\",\n" +
                "                    \"外部招聘\",\n" +
                "                    \"家政厨师\",\n" +
                "                    \"公司选址\",\n" +
                "                    \"内勤\",\n" +
                "                    \"申报税务\",\n" +
                "                    \"程度\",\n" +
                "                    \"解释\",\n" +
                "                    \"各大高校\",\n" +
                "                    \"领导团队\",\n" +
                "                    \"领导\",\n" +
                "                    \"申报税\",\n" +
                "                    \"主任\",\n" +
                "                    \"备案\"\n" +
                "                ],\n" +
                "                \"rank\": 3\n" +
                "            }\n" +
                "        ],\n" +
                "        \"positionName\": [\n" +
                "            \"hrbp\"\n" +
                "        ],\n" +
                "        \"searchQueries\": [],\n" +
                "        \"workEmphasis\": []\n" +
                "    },\n" +
                "    \"geekAdvantage\": \"\",\n" +
                "    \"geekEdu\": [\n" +
                "        {\n" +
                "            \"eduDegree\": \"本科\",\n" +
                "            \"eduDesc\": \"\",\n" +
                "            \"eduMajor\": \"财务管理\",\n" +
                "            \"eduSchool\": \"西安工业大学北方信息工程学院\",\n" +
                "            \"geekId\": 533174440,\n" +
                "            \"rank\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"geekId\": 533174440,\n" +
                "    \"geekProject\": [\n" +
                "        {\n" +
                "            \"geekId\": 533174440,\n" +
                "            \"projectDescription\": \"\",\n" +
                "            \"projectName\": \"人才盘点\",\n" +
                "            \"projectRoleName\": \"HRBP经理\",\n" +
                "            \"rank\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"geekWork\": [\n" +
                "        {\n" +
                "            \"company\": \"某生物医疗科技集团公司\",\n" +
                "            \"geekId\": 533174440,\n" +
                "            \"industry\": \"医疗设备/器械\",\n" +
                "            \"rank\": 1,\n" +
                "            \"workPositionName\": \"HRBP\",\n" +
                "            \"workResponsibility\": \"准撰写，于每年4季度完成事业部全员任职资格评价体系的搭建与评价考核，应用于次年员工调岗调薪激励中。\\n7、事业群企业文化活动：员工关怀、节日福利、季度半年度团建活动策划实施、日常荣誉积分制评比等。\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"company\": \"志学教育科技（上海）有限公司\",\n" +
                "            \"geekId\": 533174440,\n" +
                "            \"industry\": \"培训机构\",\n" +
                "            \"rank\": 2,\n" +
                "            \"workPositionName\": \"HRBP\",\n" +
                "            \"workResponsibility\": \"DP  共建共识,帮助员工通过各项工具实现资深成长跟新。 \\n4、人才数量质量把控:西安团队0-1组建工作,做好 HC  管理,月度招聘需求目标沟通,开发各种免费招聘渠道,保障满编率;参与基层和核心骨干岗位面试,就岗位、文化适配度等基于建议;合理制定淘汰策略。 \\n三、文化层面 \\n1、文化价值观传递:组织西安企业文化培训落地,结合岗位价值观传递企业核心价值观,进行职业规划塑造。\\n2、文化活动:定期生日会、团建的举办,输出相应短视频/H5宣传,落地企业文化,增加员工归属感及节日仪式感。 \\n四、团队建设及带训 \\n1、人事团队合理规划与搭建,下属最少1人,最多4人。 \\n2、团队管理和高效率任务回收,分工明确,起到标杆部门作用。 \\n主要业绩 : \\n区域从0-1团队、人事行政规章制度的搭建;组织流程和企业文化落地与升级;人才梯队、人才库建设与储备;协助业务层面进行区域战略落地与实施。\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"company\": \"中国一冶集团有限公司陕西分公司\",\n" +
                "            \"geekId\": 533174440,\n" +
                "            \"industry\": \"工程施工\",\n" +
                "            \"rank\": 3,\n" +
                "            \"workPositionName\": \"人力资源专员/助理\",\n" +
                "            \"workResponsibility\": \"导进行公司选址和员工宿舍的搬迁; \\n2、招聘家政厨师,打理内勤工作,核算领导团队的生活费用预算与支出; \\n3、同时利用所学知识,每月按时申报税务信息,为公司减少一定人力成本。 \\n4、协助总经办主任开展外部招聘工作,与西安市人才市场、各大高校签订备案信息,了解市场人才动向,为公司业务部门的招聘压力减轻负担。了解一定程度本市及本行政区内社会保障方面的法律、法规和政策,及时向相关部门及人员传达政策、意见,配合有关工作人员做好社会保障政策方面的解释工作。\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"opType\": 0,\n" +
                "    \"positionName\": \"HRBP\",\n" +
                "    \"searchQueries\": [\n" +
                "        \"德科\"\n" +
                "    ],\n" +
                "    \"ts\": 1705302758550,\n" +
                "    \"workEmphasis\": []\n" +
                "}";
        ExpTextFeature expTextFeature = JSON.parseObject(origMsg, ExpTextFeature.class);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("exps", Lists.newArrayList(expTextFeature));
        JSONArray jsonArray = CamelNameJsonUtil.keyLowerCamelToUnderScore(Lists.newArrayList(expTextFeature));
        System.out.println(jsonArray.toJSONString());
    }


    @Data
    static class ClassA {
        private int aField = 1;
        private String BField = "2";
        private Double cDoubleField = 3.;
    }

    @Data
    static class ClassB {
        private ClassA classA = new ClassA();
        private short dShortField = (short) 4;
    }

    @Data
    static class ClassC {
        private ClassB classB = new ClassB();
        private Object nullField = null;
        private long longField = 5L;
    }

    @Test
    public void keyLowerCamelToUnderScoreObject2() {
        // 三层结构测试
        ClassC classC = new ClassC();
        String msg = JSON.toJSONString(classC, JSONWriter.Feature.WriteMapNullValue);
        JSONObject newJsonObj = CamelNameJsonUtil.keyLowerCamelToUnderScore(JSON.parseObject(msg), 0);
        System.out.println(newJsonObj.toJSONString(JSONWriter.Feature.WriteMapNullValue));
    }

    @Data
    static class ClassD {
        private List<ClassB> bList = Lists.newArrayList(new ClassB(), new ClassB());
        private String dClassName = "ClassD";
    }

    @Test
    public void keyLowerCamelToUnderScoreArray1() {
        // 包含一层 list
        ClassD classD = new ClassD();
        String msg = JSON.toJSONString(classD, JSONWriter.Feature.WriteMapNullValue);
        JSONObject newJsonObj = CamelNameJsonUtil.keyLowerCamelToUnderScore(JSON.parseObject(msg), 0);
        System.out.println(newJsonObj.toJSONString(JSONWriter.Feature.WriteMapNullValue));
    }

    @Data
    static class ClassE {
        private int[][] arrayTwoDim = new int[][]{{1, 2, 3}, {4, 5, 6}};
        private String eClassName = "classE";
    }

    @Test
    public void keyLowerCamelToUnderScoreArray2() {
        // 包含两层 list
        ClassE classE = new ClassE();
        String msg = JSON.toJSONString(classE, JSONWriter.Feature.WriteMapNullValue);
        JSONObject jsonObject = JSON.parseObject(msg);
        JSONObject newJsonObj = CamelNameJsonUtil.keyLowerCamelToUnderScore(jsonObject, 0);
        System.out.println(newJsonObj.toJSONString(JSONWriter.Feature.WriteMapNullValue));
    }

    @Data
    static class ClassF {
        private String fClassName = "ClassF";
        private ClassF nextClassF;
    }

    @Test
    public void keyLowerCamelToUnderScoreArray3() {
        // 包含循环引用,测试不会死循环
        ClassF classF = new ClassF();
        String msg = JSON.toJSONString(classF, JSONWriter.Feature.WriteMapNullValue);
        JSONObject jsonObject = JSON.parseObject(msg);
        jsonObject.put("nextClassF", jsonObject);
        JSONObject newJsonObj = CamelNameJsonUtil.keyLowerCamelToUnderScore(jsonObject, 0);
        System.out.println(newJsonObj.keySet());

        // StackOverflowError
        // System.out.println(newJsonObj.toJSONString());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecallExpectFields implements BinLogUtil.FocusMsgFields {
        private long id;
        private long geekId;
        private long expId;
        private int city;
        private int position;

        @Override
        public BinLogUtil.FocusMsgFields setByJsonObject(JSONObject jsonObject) {
            this.setId(jsonObject.getLong("id"));
            this.setGeekId(jsonObject.getLong("geek_id"));
            this.setExpId(jsonObject.getLong("expect_id"));
            this.setCity(jsonObject.getInteger("city"));
            this.setPosition(jsonObject.getInteger("position"));
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RecallExpectFields that = (RecallExpectFields) o;
            return id == that.id && geekId == that.geekId && expId == that.expId && city == that.city && position == that.position;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, geekId, expId, city, position);
        }
    }

    /**
     * 牛人 工作经历
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkExperience {
        private Long geekId;
        private Integer rank;
        private String company;
        private String industry;
        private String workPositionName;
        private String workResponsibility;
    }

    /**
     * 牛人 项目经历
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectExperience {
        private Long geekId;
        private Integer rank;
        private String projectName;
        private String projectRoleName;
        private String projectDescription;
    }

    /**
     * 牛人 教育经历
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EduExperience {
        private Long geekId;
        private Integer rank;
        private String eduSchool;
        private String eduMajor;
        private String eduDegree;
        private String eduDesc;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeekKeywordCands {
        private Integer rank;
        private List<String> keywordCands;
    }

    /**
     * 期望个性化关键词结果
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpIndividualKw {
        private List<String> geekWorkKeywords;
        private List<String> geekProjectKeywords;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpKeywordCandidates {
        private Long expectId;
        private Long geekId;
        private List<String> positionName;
        private List<String> bobV2;
        private List<String> exposeCards;
        private List<String> workEmphasis;
        private List<String> searchQueries;
        private List<GeekKeywordCands> geekWork;
        private List<GeekKeywordCands> geekProject;
        private List<GeekKeywordCands> geekEdu;
        private List<String> geekAdvantage;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobKeywordCandidates {
        private Long jobId;
        private Long bossId;
        private List<String> positionName;
        private List<String> bobV2;
        private List<String> exposeCards;
        private List<String> jobSkills;
        private List<String> searchQueries;
        private List<String> jobTitle;
        private List<String> jobDesc;
    }

    /**
     * 期望: 文本特征的所有字段
     * notice 字段名不可随意变更,需要与 python 接口参数对应
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpTextFeature {
        private long expectId;
        private long geekId;
        private String opType;
        /**
         * 期望的 position name
         */
        private String positionName;
        /**
         * bob 词
         */
        private List<String> bobV2;
        /**
         * exp 在 boss 端曝光的 card 词
         */
        private List<String> exposeCards;
        // exp 维度: 技能词
        private List<String> workEmphasis;
        // exp 维度: 搜索词
        private List<String> searchQueries;
        /**
         * geek 维度的特征: 工作经历
         */
        private List<WorkExperience> geekWork;
        /**
         * geek 维度的特征: 项目经历
         */
        private List<ProjectExperience> geekProject;
        /**
         * geek 维度的特征: 教育经历
         */
        private List<EduExperience> geekEdu;
        /**
         * geek 维度的特征: 个人优势
         */
        private String geekAdvantage;
        /**
         * 记录生成的时间戳
         */
        private long ts;
        /**
         * 拼装的所有文本
         */
        private String doc;
        /**
         * 候选关键词
         */
        private List<String> keywordCands;
        /**
         * 关键词及其打分
         */
        private Map<String, Float> keywordCandsScores;
        /**
         * 分字段关键词
         */
        private ExpKeywordCandidates fieldKeywords;
        /**
         * 个性化数据(黑钻特征)
         */
        private ExpIndividualKw individualData;
    }
}
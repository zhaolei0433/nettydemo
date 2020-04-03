package test1;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhaolei
 * Create: 2019/6/24 12:12
 * Modified By:
 * Description:
 */
public class Analysis {


    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    public static void main(String[] args) {
        String htmlStr = "<p>发展夜间经济是服务业和消费升级重要推动力，也是区域经济繁荣、社会发展活力的体现。昨晚，杨浦区举办了“活力街区·魅力夜市”促进夜间经济发展启动仪式，杨浦区首位“夜间区长”和首批“夜生活首席执行官”集体亮相，共同推动区域消费升级、夜间经济发展以及小店经济繁荣。</p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/f99e6eaf8dc34c29bbd80dac51f081de.jpg\"></p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/2a5a6066802d432ca1c8f5dc0c1a9170.jpg\"></p><p class=\"ql-align-center\">图片说明：杨浦区首位“夜间区长”诞生（摄影：王修远）</p><p>围绕打造“国际范”“上海味”“时尚潮”夜生活集聚区的建设目标，杨浦区副区长赵亮作为杨浦区首位“夜间区长”将统筹协调全区夜间经济发展，七位具有深厚行业管理经验的“夜生活首席执行官”均来自不同商业企业，他们将协助“夜间区长”共同推动杨浦夜市的繁荣。</p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/5bae508e9a6d4698adec6ef64c98d502.jpg\"></p><p class=\"ql-align-center\">图片说明：杨浦区首批“夜生活首席执行官”亮相（摄影：王修远）</p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/4587dbfe5d884be7ad5e300b7603b7cd.jpg\"></p><p>“我们将美国硅谷科技的创业精神和巴黎左岸的创意文化融合起来，打造工作、生活、学习、休闲于一体的国际性综合社区。”新上任的“夜生活首席执行官”、瑞安管理（上海）有限公司创智天地项目总经理陈丽丹告诉记者，这些商圈、优质的商铺和消费者共生共荣，一起打造了充满人情味，非常有创意、活力、温暖的大家庭。未来，我们将更深层次地挖掘多元夜间经济形态，引入更多符合潮流、适合年轻人的业态，并且做好全周期的精细化服务和管理。</p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/2d2e3b76170b41438a1c58dd7f4b1e93.jpg\"></p><p>经多年努力，杨浦已拥有2条市级特色商业街区、1家全国标准化社区商业中心和1家市级社区商业示范社区。</p><p>大学路特色商业街，全场长约700米，毗邻复旦大学，沿街露天咖啡馆、文化书吧、特色餐厅、酒吧、创意零售等特色小商铺近80家，已成为创新创业群体及年轻人夜间消费的新去处。</p><p>“太平洋森活天地”地下商业街，全长近500米，总面积21000平方米，是目前上海规模最大、档次最高、最有特色的地铁地下商业载体，两侧开设了特色餐饮、生活休闲、文创小店超过140家，为地铁客流、周边白领、青年群体提供了便利、快速消费的场所。近期，又新增设了“NOYA”街区，主打滑板、潮流音乐、时尚潮品等，引领夜生活新时尚。</p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/799a3599d3564bc594825787e87fb933.jpg\"></p><p class=\"ql-align-center\"><img src=\"http://city.eastday.com/images/thumbnailimg/month_1905/9e894112eb5d4409ba33413c3e67e345.jpg\"></p><p>据介绍，杨浦区还推出了促进消费十大举措，以发展夜间经济为起点，将陆续推出包括打造新零售消费体验、扶持海派特色小店发展、鼓励文艺演出进驻商场、焕发老字号品牌活力、引进潮牌首店等一系列促进消费措施，使“消费品牌最新最潮”“购物环境最优最好”“性价比最高最划算”，吸引更多消费者近悦远来。</p><p><br></p>";
        List<String> strings = Arrays.asList(htmlStr.split("</p>")).stream().filter(s -> s != null || !s.equals("")).collect(Collectors.toList());
        strings.forEach(System.out::println);
       /* List<String> newStrings = strings.stream().map(s -> {
            return delHTMLTag(s);
        }).collect(Collectors.toList());

        newStrings.forEach(System.out::println);*/

    }

}

package series;

import cn.hutool.core.collection.CollectionUtil;
import net.bytebuddy.implementation.InvokeDynamic;

import java.util.Stack;

/**
 * 有关栈相关的题目
 * @author zyf
 * @date 2021-07-18 11:07
 */
public class StackSeries {


    /**
     * 有效的括号
     * 给定一个只包括 ‘(‘，’)’，'{‘，’}’，'<‘，’>’ 的字符串 s ，判断字符串是否有效。
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * @param s 是一个字符串
     * @return 返回一个布尔类型
     */
    public static boolean bracketJudgeValid(String s) {
        // 先判断是否偶数, 如果不是偶数, 肯定不会闭合
        if (s == null || s.length() % 2 == 1) {
            return false;
        }

        // 构建栈
        Stack<Character> stack = new Stack<>();

        // 遍历所有元素, 把元素挨个比较
        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(')');
            } else if ('{' == c) {
                stack.push('}');
            } else if ('<' == c) {
                stack.push('>');
                // 这里因为是对称的, 所以当前面都miss以后, 这里就开始从内到外开始匹配是否配对, 如果没有配对直接false
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

}

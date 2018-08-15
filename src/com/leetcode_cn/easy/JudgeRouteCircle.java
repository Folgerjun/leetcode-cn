package com.leetcode_cn.easy;

/****************判断路线成圈****************/
/**
 * 初始位置 (0, 0) 处有一个机器人。给出它的一系列动作，判断这个机器人的移动路线是否形成一个圆圈，换言之就是判断它是否会移回到原来的位置。
 * 
 * 移动顺序由一个字符串表示。每一个动作都是由一个字符来表示的。机器人有效的动作有 R（右），L（左），U（上）和 D（下）。输出应为 true 或
 * false，表示机器人移动路线是否成圈。
 * 
 * 示例 1:
 * 
 * 输入: "UD" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: "LL" 输出: false
 * 
 * @author ffj
 *
 */
public class JudgeRouteCircle {

	public boolean judgeCircle(String moves) {

		if (moves == null || moves.length() == 0)
			return true;
		int x = 0, y = 0;
		for (int i = 0; i < moves.length(); i++) {
			char ch = moves.charAt(i);
			switch (ch) {
			case 'R':
				x += 1;
				break;
			case 'L':
				x -= 1;
				break;
			case 'U':
				y += 1;
				break;
			case 'D':
				y -= 1;
				break;
			default:
				break;
			}
		}
		return x == 0 && y == 0;
	}

	/**
	 * 
	 * @param moves
	 * @return
	 */
	public boolean judgeCircle1(String moves) {
		int x = 0, y = 0;
		for (char move : moves.toCharArray()) {
			if (move == 'U')
				y--;
			else if (move == 'D')
				y++;
			else if (move == 'L')
				x--;
			else if (move == 'R')
				x++;
		}
		return x == 0 && y == 0;
	}

}

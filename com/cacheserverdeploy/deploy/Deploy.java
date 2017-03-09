package com.cacheserverdeploy.deploy;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.Type;

public class Deploy {
	/**
	 * 你需要完成的入口 <功能详细描述>
	 * 
	 * @param graphContent
	 *            用例信息文件
	 * @return [参数说明] 输出结果信息
	 * @see [类、类#方法、类#成员]
	 */

	/**
	 * 打印数组信息
	 * 
	 * @param arr
	 *            数组
	 */
	public static void printArr(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static int[] getShortestDistance(int[][] arr, int startNode) {
		final int MAX_VALUE = 2147483647;
		// int[][] arr={{0,6,3,MAX_VALUE,MAX_VALUE,MAX_VALUE},
		// {6,0,2,5,MAX_VALUE,MAX_VALUE,MAX_VALUE},
		// {3,2,0,3,4,MAX_VALUE},
		// {MAX_VALUE,5,3,0,2,3},
		// {MAX_VALUE,MAX_VALUE,4,2,0,5},
		// {MAX_VALUE,MAX_VALUE,MAX_VALUE,3,5,0}};

		int nodeNum = arr.length; // 网络节点的个数
		int[][] distance = arr; // 网络节点之间

		// int startNode = 0; // 起始节点ID
		int[] shortestDis = arr[startNode]; // 当前起始节点到各节点的最短距离

		int[] selectOpt = new int[nodeNum]; // 节点选择标示集，选择的值为1，未选择的值为0

		// 节点选择标示集的初始化
		for (int i = 0; i < nodeNum; i++) {
			selectOpt[i] = 0;
		}

		int currentNode = startNode; // 当前选择节点ID
		int count = 0; // 循环次数

		while (true) {
			count = count + 1;
			int currentShortestDis = shortestDis[currentNode]; // 起始节点到当前节点的最短距离
			selectOpt[currentNode] = 1; // 选择的节点标记为1
			int nextNode = currentNode; // 下一个节点
			int shortestPath = MAX_VALUE; // 当前节点与下一个节点间的最短距离
			for (int i = 0; i < nodeNum; i++) {
				if (selectOpt[i] == 0 && distance[currentNode][i] < MAX_VALUE) {

					// 如果起始节点到某个节点出现更短的路线，更新起始节点到该节点的距离
					int newDis = currentShortestDis + distance[currentNode][i];
					if (newDis < shortestDis[i]) {
						shortestDis[i] = newDis;
					}

					// 选择作为当前节点的下一个节点
					if (distance[currentNode][i] < shortestPath) {
						shortestPath = distance[currentNode][i];
						nextNode = i;
					}

				}
			}

			if (nextNode == currentNode) {
				System.out
						.println("开始节点:" + startNode + "\t" + "循环次数：" + count);
				break;
			}

			currentNode = nextNode;

		}

		// for (int i = 0; i < nodeNum; i++) {
		// System.out.println(shortestDis[i] + " ");
		// }

		return shortestDis;
	}

	public static int[][] getNodeArr(String[] LinkInfo, int NetNodeNum) {
		final int MAX_VALUE = 2147483647;
		int[][] nodeArr = new int[NetNodeNum][NetNodeNum];

		for (int i = 0; i < NetNodeNum; i++) {
			for (int j = 0; j < NetNodeNum; j++) {
				if (i==j){
					nodeArr[i][j] = 0;
				}
				else{
					nodeArr[i][j] = MAX_VALUE;
				}
				
			}
		}

		for (int i = 0; i < LinkInfo.length; i++) {
			String[] NodeInfo = LinkInfo[i].split(" ");
			int node1 = Integer.parseInt(NodeInfo[0]);
			int node2 = Integer.parseInt(NodeInfo[1]);
			int weight = Integer.parseInt(NodeInfo[3]);
			nodeArr[node1][node2] = weight;
			nodeArr[node2][node1] = weight;
		}

		return nodeArr;
	}

	public static String[] deployServer(String[] graphContent) {
		/** do your work here **/

		// printArr(graphContent);
		String[] NetInfo = graphContent[0].split(" ");
		Integer NetNodeNum = Integer.parseInt(NetInfo[0]); // 网络节点数量
		Integer NetLinkNum = Integer.parseInt(NetInfo[1]); // 网络链路数量
		Integer ConsumeNodeNum = Integer.parseInt(NetInfo[2]); // 消费节点数量
		Integer ServerCost = Integer
				.parseInt((graphContent[2].split("\r\n"))[0]); // 视频内容服务器部署成本
		String[] LinkInfo = Arrays.copyOfRange(graphContent, 4, 4 + NetLinkNum); // 链路信息
		String[] ConsumeInfo = Arrays.copyOfRange(graphContent, 5 + NetLinkNum,
				5 + NetLinkNum + ConsumeNodeNum); // 消费节点信息

		int[][] nodeArr = getNodeArr(LinkInfo, NetNodeNum);

		// for (int i = 0; i < NetNodeNum; i++) {
		// for (int j = 0; j < NetNodeNum; j++) {
		// System.out.print(nodeArr[i][j]+" ");
		// }
		// System.out.println("");
		// }

		int[] result = getShortestDistance(nodeArr, 0);

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}

		return new String[] { "17", "\r\n", "0 8 0 20" };
	}

}

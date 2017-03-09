package com.cacheserverdeploy.deploy;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.Type;

public class Deploy {
	/**
	 * ����Ҫ��ɵ���� <������ϸ����>
	 * 
	 * @param graphContent
	 *            ������Ϣ�ļ�
	 * @return [����˵��] ��������Ϣ
	 * @see [�ࡢ��#��������#��Ա]
	 */

	/**
	 * ��ӡ������Ϣ
	 * 
	 * @param arr
	 *            ����
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

		int nodeNum = arr.length; // ����ڵ�ĸ���
		int[][] distance = arr; // ����ڵ�֮��

		// int startNode = 0; // ��ʼ�ڵ�ID
		int[] shortestDis = arr[startNode]; // ��ǰ��ʼ�ڵ㵽���ڵ����̾���

		int[] selectOpt = new int[nodeNum]; // �ڵ�ѡ���ʾ����ѡ���ֵΪ1��δѡ���ֵΪ0

		// �ڵ�ѡ���ʾ���ĳ�ʼ��
		for (int i = 0; i < nodeNum; i++) {
			selectOpt[i] = 0;
		}

		int currentNode = startNode; // ��ǰѡ��ڵ�ID
		int count = 0; // ѭ������

		while (true) {
			count = count + 1;
			int currentShortestDis = shortestDis[currentNode]; // ��ʼ�ڵ㵽��ǰ�ڵ����̾���
			selectOpt[currentNode] = 1; // ѡ��Ľڵ���Ϊ1
			int nextNode = currentNode; // ��һ���ڵ�
			int shortestPath = MAX_VALUE; // ��ǰ�ڵ�����һ���ڵ�����̾���
			for (int i = 0; i < nodeNum; i++) {
				if (selectOpt[i] == 0 && distance[currentNode][i] < MAX_VALUE) {

					// �����ʼ�ڵ㵽ĳ���ڵ���ָ��̵�·�ߣ�������ʼ�ڵ㵽�ýڵ�ľ���
					int newDis = currentShortestDis + distance[currentNode][i];
					if (newDis < shortestDis[i]) {
						shortestDis[i] = newDis;
					}

					// ѡ����Ϊ��ǰ�ڵ����һ���ڵ�
					if (distance[currentNode][i] < shortestPath) {
						shortestPath = distance[currentNode][i];
						nextNode = i;
					}

				}
			}

			if (nextNode == currentNode) {
				System.out
						.println("��ʼ�ڵ�:" + startNode + "\t" + "ѭ��������" + count);
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
		Integer NetNodeNum = Integer.parseInt(NetInfo[0]); // ����ڵ�����
		Integer NetLinkNum = Integer.parseInt(NetInfo[1]); // ������·����
		Integer ConsumeNodeNum = Integer.parseInt(NetInfo[2]); // ���ѽڵ�����
		Integer ServerCost = Integer
				.parseInt((graphContent[2].split("\r\n"))[0]); // ��Ƶ���ݷ���������ɱ�
		String[] LinkInfo = Arrays.copyOfRange(graphContent, 4, 4 + NetLinkNum); // ��·��Ϣ
		String[] ConsumeInfo = Arrays.copyOfRange(graphContent, 5 + NetLinkNum,
				5 + NetLinkNum + ConsumeNodeNum); // ���ѽڵ���Ϣ

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

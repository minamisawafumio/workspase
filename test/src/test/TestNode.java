package test;

import java.util.List;

import org.junit.Test;

import shogi.bean.Node;
import shogi2.common.ShogiConst2;

public class TestNode {

	@Test
	public void test_Node() {
		int inDepth = 0;

		Node node = new Node();

		node.setTeban(ShogiConst2.TEBAN_SENTE);

		node = meke(inDepth, null, node);

		serch(node);

		Node koNode = node.getKoNode();

		System.out.println("koNode No = " + koNode.getNodeNo() + " point = " + koNode.getPoint());
	}


	/**
	 * ノードを作成する
	 * @param inDepth
	 * @param oyaNode
	 * @return
	 */
	private Node meke(int inDepth, Node oyaNodeAa, Node oyaNode) {

		int depth = inDepth + 1;

		if (depth > 2) {

			int point = TestCommon.makeArrayPoint();

			oyaNode.setPoint(point);

			return oyaNode;
		}

		int koTeban = oyaNode.getTeban() * -1;

		for (int i = 0; i < 3; i++) {

			Node koNode = TestCommon.makeNode(depth, koTeban);

			koNode.setOyaNode(oyaNode);

			Node newNode = meke(depth, oyaNodeAa, koNode);

			oyaNode.getNodeList().add(newNode);
		}

		return oyaNode;
	}


	/**
	 * 枝探索
	 * @param oyaNode
	 */
	private void serch(Node oyaNode2) {

		List<Node> nodeList = oyaNode2.getNodeList();

		int size = nodeList.size();

		if (size == 0) {
			return ;
		}

		for (int i = 0; i < size; i++) {
			Node ko = nodeList.get(i);

			serch(ko);

			Node oyaNode = ko.getOyaNode();

			if (oyaNode.getTeban() == ShogiConst2.TEBAN_GOTE) {
				if(oyaNode.getPoint() >  ko.getPoint()) {
					oyaNode.setPoint(ko.getPoint());
					oyaNode.setKoNode(ko);
				}
			} else {
				if(oyaNode.getPoint() <  ko.getPoint()) {
					oyaNode.setPoint(ko.getPoint());
					oyaNode.setKoNode(ko);
				}
			}

			if (isBranchCut(oyaNode) == true) {
				break;
			}


			System.out.println("nodeNo=" + ko.getNodeNo() + " depth = " + ko.getDepth() + " i=" + i + " point = " + ko.getPoint());
		}

		return ;
	}


	/**
	 * 枝刈り処理
	 * @param node
	 * @return
	 */
	private boolean isBranchCut(Node node) {
		if(node.getDepth() < 1) {
			return false;
		}

		int point = node.getPoint();
		int oYaOyaNodePoint = node.getOyaNode().getPoint();

		if (node.getTeban() == ShogiConst2.TEBAN_GOTE) {
			if(point <= oYaOyaNodePoint) {
				System.out.println("枝刈り実施 nodeNo" + node.getNodeNo()  + " point = " + node.getPoint());
				return true;
			}
		} else {
			if(point >= oYaOyaNodePoint) {
				System.out.println("枝刈り実施 nodeNo" + node.getNodeNo()  + " point = " + node.getPoint());
				return true;
			}
		}
		return false;
	}
}

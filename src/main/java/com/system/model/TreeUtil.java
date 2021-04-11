package com.system.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 樹工具欄
 * @author Ablett_Chen
 *
 */

public class TreeUtil {

    public static List<SysMenuTree> toTree(List<SysMenuTree> treeList, Integer pid) {
        List<SysMenuTree> retList = new ArrayList<SysMenuTree>();
        for (SysMenuTree parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }
    private static SysMenuTree findChildren(SysMenuTree parent, List<SysMenuTree> treeList) {
        for (SysMenuTree child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
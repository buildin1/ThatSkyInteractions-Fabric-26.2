![Banner](./images/banner.png)

# ThatSkyInteractions（Fabric 26.2 移植版）

> 本项目为 [ThatSkyInteractions](https://github.com/LouisQuepierts/ThatSkyInteractions) 的 Fabric 26.2 移植分支。
> 在保留原项目《Sky 光·遇》风格非竞争性社交交互玩法的基础上，迁移至 Fabric 加载器，并同时兼容 OpenGL 与 Vulkan 渲染后端。

---

## 平台支持

| 平台 | 状态 | 说明 |
|---|---|---|
| **Fabric 26.2** | ✅ 支持 | 客户端主版本 |
| **OpenGL** | ✅ 支持 | 默认渲染后端 |
| **Vulkan** | ✅ 支持 | 实验性渲染后端 |
| **Paper / Folia 服务端** | ✅ 支持 | 需安装配套服务端插件 |
| **NeoForge / Forge** | ❌ 不支持 | 本分支仅维护 Fabric 版本 |

---

## 服务端插件

本 Mod 需要配套的服务端插件来处理玩家关系、交互树解锁、星盘数据等逻辑。

请前往以下仓库下载对应版本的服务端插件：

- **Paper / Folia 服务端插件**：https://github.com/buildin1/ThatSkyInteractions-Folia-Plugin

> 服务端插件尚未发布时，该链接可能为空。可关注本仓库后续更新。

---

## 主要特性

### 交互树（Interaction Tree）

空手并按下交互快捷键后，右键其他玩家可打开交互树界面。

交互树复刻了《Sky 光·遇》的好友系统，包含：

- 情感动作与手势
- 屏蔽、重命名等实用选项
- 需要双方确认才能执行的解锁型交互
- 消耗普通蜡烛或红蜡烛逐步解锁节点

<table>
  <tr>
    <td>
      <img src="./images/interaction_tree_locked.png" alt="交互树" width="350"/>
    </td>
    <td>
      <img src="./images/interaction_tree_unlocked.png" alt="已解锁交互树" width="350"/>
    </td>
  </tr>
</table>

### 建筑方块

![建筑方块](./images/banner_blocks.png)

#### 蜡烛

本 Mod 提供 **16 种可自定义蜡烛**，可在单个方块空间内自由摆放，每种蜡烛拥有独立的样式、位置与状态。

> 性能提示：蜡烛系统已针对渲染效率做大量优化，即使视野内蜡烛数量较多也能流畅运行。

#### 光之翼（Wing of Light）

带有泛光效果的玩家装饰，灵感来自《Sky 光·遇》的美学风格。

### 星盘（Astrolabe，开发中）

未来将通过快捷键打开好友总览界面，查看已建立连接的好友，并可能支持快速导航。

---

## 数据驱动设计

ThatSkyInteractions 支持通过 Minecraft 数据包自定义交互树。

你可以通过编辑 [friend.json](./src/main/resources/data/thatskyinteractions/interact_trees/friend.json) 添加自己的交互动作。

<details>
<summary>交互树示例</summary>

```json5
{
  "root": "root",                                  // 根节点 ID
  "nodes": [                                       // 节点定义
    {
      "id": "root",                                // 节点 ID，需唯一
      "type": "friend",                            // 建议根节点使用 "friend"
      "price": 3,                                  // 解锁价格
      "left": "like",                              // 左分支节点
      "middle": "high_five_1",                     // 中分支节点
      "right": "block"                             // 右分支节点
    },
    {
      "id": "high_five_1",
      "type": "interaction",                        // 交互节点
      "price": 1,                                   // 解锁价格
      "interact": "thatskyinteractions:high_five",  // 交互动画 ID（Geckolib 格式）
      "left": "high_five_2",
      "middle": "hug_1"
    },
    {
      "id": "lock_1",
      "type": "lock",                               // 锁定节点，解锁时消耗红蜡烛
      "price": 1,
      "middle": "double_high_five_1"
    }
    // ...
  ]
}
```

</details>

---

## 依赖前置

| 前置 | 用途 |
|---|---|
| [Fabric API](https://modrinth.com/mod/fabric-api) | Fabric 基础 API |
| [Geckolib](https://modrinth.com/mod/geckolib) | 动画渲染（已验证支持 Vulkan） |
| [YetAnotherConfigLib (YACL)](https://modrinth.com/mod/yacl) | 配置界面 |
| [Cardinal Components API](https://modrinth.com/mod/cardinal-components-api) | 实体/玩家数据组件（可选，视实现而定） |

---

## 构建

```bash
./gradlew build
```

构建产物位于 `build/libs/`。

---

## 鸣谢

- 原作者：**LouisQuepierts**
- 灵感来源：thatgamecompany《Sky 光·遇》
- 所有设计参考仅用于教育与非营利目的
- 原项目曾参展 TeaCon 甲辰（https://www.teacon.cn/jiachen/）

---

## 许可证

本项目为原 ThatSkyInteractions 的 Fork，具体许可证请参照原仓库。

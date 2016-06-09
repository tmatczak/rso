package com.example.ResponseObjects;

import com.example.util.NodeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Tobiasz on 2016-06-09.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NodesResponse {
    private NodeInfo coordinator;
    private List<NodeInfo> externalNodes;

}

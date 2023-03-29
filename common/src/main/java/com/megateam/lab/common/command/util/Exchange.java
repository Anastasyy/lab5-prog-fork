package com.megateam.lab.common.command.util;

import com.megateam.lab.common.command.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Builder(setterPrefix = "set")
@Getter
@Setter
public class Exchange {
  @NonNull private Command command;
  @NonNull private List<String> args;
}

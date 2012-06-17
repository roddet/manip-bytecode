package com.roddet.agent;

import com.sun.tools.attach.VirtualMachine;

public class MyAgentLiveInstaller {

    public static void main(String[] args) throws Exception {
        VirtualMachine vm = null;
        try {
            // 2 parameters for this execution
            if(args.length < 2) {
                throw new RuntimeException("Two parameters is mandatory : pid and agent JAR path");
            }

            String pid = args[0];
            String agent = args[1];

            System.out.println("Attaching agent " + agent + " to application with pid " + pid);

            // Attach VM to running application with his pid
            vm = VirtualMachine.attach(pid);

            // load agent into target VM
            vm.loadAgent(agent);

            System.out.println("Agent loaded + " + vm.getAgentProperties());

        } finally {
            if(vm != null) {
                vm.detach();
            }
        }

    }
}

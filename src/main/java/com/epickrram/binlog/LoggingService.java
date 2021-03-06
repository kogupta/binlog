package com.epickrram.binlog;

//////////////////////////////////////////////////////////////////////////////////
//   Copyright 2011   Mark Price     mark at epickrram.com                      //
//                                                                              //
//   Licensed under the Apache License, Version 2.0 (the "License");            //
//   you may not use this file except in compliance with the License.           //
//   You may obtain a copy of the License at                                    //
//                                                                              //
//       http://www.apache.org/licenses/LICENSE-2.0                             //
//                                                                              //
//   Unless required by applicable law or agreed to in writing, software        //
//   distributed under the License is distributed on an "AS IS" BASIS,          //
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   //
//   See the License for the specific language governing permissions and        //
//   limitations under the License.                                             //
//////////////////////////////////////////////////////////////////////////////////


public final class LoggingService
{
    private ThreadLocal<SingleThreadedLoggingEvent> singleThreadedEvent =
            new ThreadLocal<SingleThreadedLoggingEvent>();
    private ThreadLocal<LoggingEvent> multiThreadedEvent = new ThreadLocal<LoggingEvent>();

    private final DirectMemoryLogger logger;

    public LoggingService(final DirectMemoryLogger logger)
    {
        this.logger = logger;
    }

    public LoggingEvent singleThreadedAccess()
    {
        if(singleThreadedEvent.get() == null)
        {
            singleThreadedEvent.set(new SingleThreadedLoggingEvent(logger, new SingleThreadedLoggingCapacityAllocator()));
        }

        return singleThreadedEvent.get();
    }

    public LoggingEvent multiThreadedAccess()
    {
        if(multiThreadedEvent.get() == null)
        {
            multiThreadedEvent.set(new MultiThreadedLoggingEvent(logger, new MultiThreadedLoggingCapacityAllocator()));
        }

        return multiThreadedEvent.get();
    }
}

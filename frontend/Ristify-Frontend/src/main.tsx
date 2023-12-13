// import React from 'react'
import ReactDOM from 'react-dom/client'
import './styles/global.scss'
import App from "./App.tsx";
import {QueryClient, QueryClientProvider} from "react-query";
import {ChakraProvider} from '@chakra-ui/react'
import {theme} from "./theme/theme.ts";

const queryClient: QueryClient = new QueryClient();


ReactDOM.createRoot(document.getElementById('root')!).render(
    <QueryClientProvider client={queryClient}>
        <ChakraProvider theme={theme}>
            <App/>
        </ChakraProvider>
    </QueryClientProvider>
)
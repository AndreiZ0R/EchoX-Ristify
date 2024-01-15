import React, { useState} from 'react';
import { useNavigate } from "react-router-dom";
import {useFormik} from "formik";
import {
    Button,
    Flex,
    VStack,
    Heading,
    Image,
    Link,
    Box
} from "@chakra-ui/react";``
import CustomInput from "../../components/CustomInput/CustomInput.tsx";
import {useLogin} from "../../hooks/CustomHooks.ts";

const Login = () => {
    const [fieldTouched, setFieldTouched] = useState({
        username: false,
        password: false,
    });
    const navigate = useNavigate();
    const login = useLogin();

    const formik = useFormik({
        initialValues: {
            username: '',
            password: '',
        },
        onSubmit: (values) => {
            login.mutate(values, {
                onSuccess: () => {
                    navigate("/home")
                }
            });
        }
    });

    return (
        <Box
            display="flex"
            alignItems="center"
            justifyContent="center"
            bgGradient="linear(to-b, #845ec2, #c493ff, #242424)"
            h="100vh"
            overflow="auto"
        >
            <Flex
                h="75%"
                w="75%"
                bg="background.darker"
                boxShadow="0 0 25px 15px rgba(0, 0, 0, 0.35)"
                rounded="3xl"
                direction={{base: "column", md: "row"}}
                gap={6}
            >
                <Flex
                    bg="background.base"
                    w={{base: "auto", md: "50%"}}
                    h="100%"
                    direction="column"
                    align="center"
                    justify="center"
                    rounded="3xl"
                >
                    <Image
                        src="../../assets/auth_back.jpg"
                        objectFit="cover"
                        w="100%"
                        h="100%"
                        rounded="3xl"
                    />
                </Flex>
                <Flex
                    h="75%"
                    bg="background.darker"
                    align="center"
                    justify="center"
                    direction="column"
                    rounded="3xl"
                    pl={180}
                >
                    <VStack spacing={4} align="center" w="100%">
                        <Heading
                            as="h2"
                            size="4xl"
                            textColor="background.light"
                            pt={20}
                            sx={{
                                userSelect: 'none',
                                pointerEvents: 'none',
                                textDecoration: 'none'
                            }}
                        >
                            Welcome back
                        </Heading>
                        <Heading as="h3" size="lg" textColor="primary.base" pb={10} >
                            <span
                                style={{
                                    userSelect: 'none',
                                    pointerEvents: 'none',
                                    textDecoration: 'none'
                                }}
                            >
                                Don't have an account?{' '}
                            </span>
                            <Link
                                color="primary.lighter"
                                href="/signIn"
                                _hover={{textDecoration: 'none', color: "primary.base"}}
                            >
                                Sign In
                            </Link>
                        </Heading>
                        <Flex
                            w={{base: "auto", md: "120%"}}
                            h="80%"
                            direction="column"
                            mt={20}
                        >
                            <form onSubmit={formik.handleSubmit}>
                                <CustomInput
                                    label="Username"
                                    size="lg"
                                    borderRadius="lg"
                                    type="text"
                                    name="username"
                                    onChange={formik.handleChange}
                                    onBlur={() => setFieldTouched({...fieldTouched, username: true})}
                                    value={formik.values.username}
                                    backgroundColor="background.base"
                                    color="background.light"
                                    hoverBackgroundColor="background.base"
                                    selectionBackgroundColor="gray"
                                    focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                    placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                    width="100%"
                                    fontSize="xl"
                                    fontFamily="fonts.body"
                                    autoComplete="off"
                                />
                                <CustomInput
                                    label="Password"
                                    size="lg"
                                    borderRadius="lg"
                                    type="password"
                                    name="password"
                                    onChange={formik.handleChange}
                                    onBlur={() => setFieldTouched({...fieldTouched, password: true})}
                                    value={formik.values.password}
                                    backgroundColor="background.base"
                                    color="background.light"
                                    hoverBackgroundColor="background.base"
                                    selectionBackgroundColor="gray"
                                    focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                    placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                    width="100%"
                                    fontSize="xl"
                                    fontFamily="fonts.body"
                                />
                                <Button
                                    bgColor="primary.base"
                                    color="white"
                                    _hover={{backgroundColor: "primary.lighter"}}
                                    width="full"
                                    type="submit"
                                    size="lg"
                                    mt={50}
                                    fontSize="xl"
                                    disabled={!formik.isValid}
                                >
                                    Login
                                </Button>
                            </form>
                        </Flex>
                    </VStack>
                </Flex>
            </Flex>
        </Box>
    );
}

export default Login;

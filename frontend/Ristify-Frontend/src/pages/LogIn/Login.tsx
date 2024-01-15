import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {useFormik} from "formik";
import {Box, Button, Flex, Heading, Image, Link, VStack} from "@chakra-ui/react";
import {useLogin} from "../../hooks/CustomHooks.ts";
import {LoginModel, User} from "../../models/User.ts";
import CustomInput from "../../components/CustomInput/CustomInput.tsx";

``

const Login = () => {
    const [fieldTouched, setFieldTouched] = useState({
        username: false,
        password: false,
    });
    const navigate = useNavigate();
    const {data: loggedUser, mutate: login, isSuccess} = useLogin();

    const formik = useFormik({
        initialValues: {
            username: '',
            password: '',
        },
        onSubmit: (values) => {
            login(values);
            if (isSuccess) {

                const user: User = (loggedUser.payload as LoginModel).user;
                navigate("/home", {state: {user}});
            }
        }
    });

    return (
        // wrapper
        <Box
            display="flex"
            alignItems="center"
            justifyContent="center"
            bgGradient="linear(to-b, #845ec2, #c493ff, #242424)"
            h="100vh"
        >

            {/*login card*/}
            <Box
                h={{lg:"75%", md:"75%", sm:"full", base:"full"}}
                w={{lg:"75%", md:"75%", sm:"full", base:"full"}}
                bg="background.darker"
                boxShadow="0 0 25px 15px rgba(0, 0, 0, 0.35)"
                rounded={{lg:"3xl", md:"3xl", sm:"0",base:"0"}}
                // direction={{base: "column", md: "row"}}
                display="flex"
                flexDirection={{lg: "row", md:"row", sm:"column", base:"column"}}
            >

                {/* left with pic*/}
                <Box
                    bg="background.base"
                    // w={{base: "auto", md: "50%"}}
                    h={{lg:"full", md:"full", sm: "30%", base:"30%"}}
                    w={{lg:"50%", md:"50%", sm: "full", base:"full"}}
                    // direction="column"
                    // align="center"
                    // justify="center"
                    rounded="3xl"
                >
                    <Image
                        src="../../assets/auth_back.jpg"
                        fit="cover"
                        w="100%"
                        h="100%"
                        rounded="3xl"
                    />
                </Box>

                {/*right box*/}
                <Box
                    h="full"
                    w={{lg:"50%", md:"50%", sm: "full", base:"full"}}
                >
                    <Flex
                        h="full"
                        w="full"
                        bg="background.darker"
                        align="center"
                        justify="center"
                        direction="column"
                        rounded="3xl"
                        p={5}
                    >
                        <VStack spacing={2} align="center" w="100%">
                            <Heading
                                as="h2"
                                size="4xl"
                                textColor="background.light"
                                pt={5}
                                sx={{
                                    userSelect: 'none',
                                    pointerEvents: 'none',
                                    textDecoration: 'none'
                                }}
                            >
                                Welcome back
                            </Heading>
                            <Heading as="h3" size="md" textColor="primary.base" pb={5}>
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
                                // w={{base: "auto", md: "120%"}}
                                h="full"
                                w="full"
                                direction="column"
                                mt={2}
                                // p={2}
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
                </Box>


                {/*    pana aici vericu*/}
            </Box>


        </Box>
    );
}

export default Login;
